package pfe.exambuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pfe.exambuilder.dto.AnswerDto;
import pfe.exambuilder.model.Answer;
import pfe.exambuilder.service.AnswerService;



@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerDto> createAnswer(@RequestBody AnswerDto answerDto) {
        Answer answer = new Answer();
        Answer savedAnswer = answerService.saveAnswer(answer);
        return ResponseEntity.ok(answerDto);
    }
}
