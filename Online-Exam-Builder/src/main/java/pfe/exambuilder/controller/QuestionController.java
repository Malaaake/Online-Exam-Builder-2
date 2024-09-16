package pfe.exambuilder.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pfe.exambuilder.dto.QuestionDto;
import pfe.exambuilder.model.QuestionType;
import pfe.exambuilder.service.QuestionService;



@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/exam/{examId}")
    public ResponseEntity<?> addQuestionToExam(@PathVariable Long examId, @RequestBody Question question) {
        Question newQuestion = questionService.createQuestion(question, examId);
        return ResponseEntity.ok(newQuestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question questionDetails) {
        return ResponseEntity.ok(questionService.updateQuestion(id, questionDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }
}


/*@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto) {
        Question question = new Question(null, null, null);
        questionService.saveQuestion(question);
        return ResponseEntity.ok(questionDto);
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        List<QuestionDto> questionDtos = questions.stream().map(q -> new QuestionDto(
                q.getText(),
                q.getQuestionType().name(),
                null // You can map answers here if needed
        )).collect(Collectors.toList());
        return ResponseEntity.ok(questionDtos);
    }
}
*/