package pfe.exambuilder.service;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Random;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pfe.exambuilder.exception.ResourceNotFoundException;
import pfe.exambuilder.model.Exam;
import pfe.exambuilder.repository.ExamRepository;
import pfe.exambuilder.repository.QuestionRepository;

@Service
public class QuestionService {

    private static final String MULTIPLE_CHOICE = null;
	private static final String TRUE_FALSE = null;
	private static final String SHORT_ANSWER = null;
	private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, ExamRepository examRepository) {
        this.questionRepository = questionRepository;
        this.examRepository = examRepository;
    }

    public Question createQuestion(Question question, Long examId) {
        Exam exam = examRepository.findById(examId)
            .orElseThrow();
        question.setExam(exam);
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByExamId(Long examId) {
        return questionRepository.findByExamId(examId);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));
    }

    public Question updateQuestion(Long id, Question questionDetails) {
        Question existingQuestion = getQuestionById(id);
        existingQuestion.setQuestionText(questionDetails.getQuestionText());
        existingQuestion.setQuestionType(questionDetails.getQuestionType());
        existingQuestion.setMarks(questionDetails.getMarks());
        existingQuestion.setOptions(questionDetails.getOptions());
        existingQuestion.setAnswerKey(questionDetails.getAnswerKey());
        return questionRepository.save(existingQuestion);
    }

    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id);
        }
        questionRepository.deleteById(id);
    }

    public boolean validateQuestionFormat(Question question) {
        return switch (question.getQuestionType()) {
            case MULTIPLE_CHOICE -> validateMultipleChoiceQuestion(question);
            case TRUE_FALSE -> validateTrueFalseQuestion(question);
            case SHORT_ANSWER -> validateShortAnswerQuestion(question);
            default -> false;
        };
    }

    private boolean validateMultipleChoiceQuestion(Question question) {
        if (question.getOptions() == null || question.getOptions().size() < 2) {
            return false;
        }
        return question.getOptions().stream().filter(Option::isCorrect).count() == 1;
    }

    private boolean validateTrueFalseQuestion(Question question) {
        if (question.getOptions() == null || question.getOptions().size() != 2) {
            return false;
        }
        return question.getOptions().stream().anyMatch(option -> "True".equalsIgnoreCase(option.getText()))
            && question.getOptions().stream().anyMatch(option -> "False".equalsIgnoreCase(option.getText()));
    }

    private boolean validateShortAnswerQuestion(Question question) {
        return question.getAnswerKey() != null && !question.getAnswerKey().trim().isEmpty();
    }

    public List<Question> getRandomQuestions(Long examId, int count) {
        List<Question> allQuestions = questionRepository.findByExamId(examId);
        
        if (allQuestions.size() <= count) {
            return allQuestions;
        }
        
        Random random = new Random();
        return random.ints(0, allQuestions.size())
                     .distinct()
                     .limit(count)
                     .mapToObj(allQuestions::get)
                     .collect(Collectors.toList());
    }
}


