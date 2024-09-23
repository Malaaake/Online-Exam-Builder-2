package pfe.exambuilder.controller;

import java.time.LocalDateTime;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pfe.exambuilder.dto.AnswerDto;
import pfe.exambuilder.model.Answer;
import pfe.exambuilder.repository.QuestionRepository;
import pfe.exambuilder.service.AnswerService;



@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping
    public ResponseEntity<AnswerDto> createAnswer(@RequestBody AnswerDto answerDto) {
        try {
            Answer answer = convertToEntity(answerDto);
            Answer savedAnswer = answerService.saveAnswer(answer);
            AnswerDto savedAnswerDto = convertToDto(savedAnswer);
            return ResponseEntity.ok(savedAnswerDto);
        } catch (QuestionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Answer convertToEntity(AnswerDto dto) throws QuestionNotFoundException {
        Answer answer = new Answer();
        answer.setContent(dto.getContent());
        answer.setAuthor(dto.getAuthor());
        answer.setCreatedAt(LocalDateTime.now());
        
        Question question = questionRepository.findById(dto.getQuestionId())
            .orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + dto.getQuestionId()));
        answer.setQuestion(question);
        
        return answer;
    }

    private AnswerDto convertToDto(Answer entity) {
        AnswerDto dto = new AnswerDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setAuthor(entity.getAuthor());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setQuestionId(entity.getQuestion().getId());
        return dto;
    }
}

class QuestionNotFoundException extends Exception {
    public QuestionNotFoundException(String message) {
        super(message);
    }
}
