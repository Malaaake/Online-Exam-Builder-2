package pfe.exambuilder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pfe.exambuilder.model.Exam;
import pfe.exambuilder.repository.ExamRepository;



@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "*") // Allowing cross-origin requests from Angular frontend
public class ExamController {

    @Autowired
    private ExamRepository examRepository;

    // Get all exams
    @GetMapping
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    // Get exam by ID
    @GetMapping("/{id}")
    public Exam getExamById(@PathVariable Long id) {
        return examRepository.findById(id).orElse(null);
    }

    // Create a new exam
    @PostMapping("/api/exams")
    public Exam createExam(@RequestBody Exam exam) {
        return  examRepository.save(exam);
    }

    // Update an exam
    @PutMapping("/{id}")
    public Exam updateExam(@PathVariable Long id, @RequestBody Exam examDetails) {
        Exam exam = examRepository.findById(id).orElse(null);

        if (exam != null) {
            exam.setTitle(examDetails.getTitle());
            exam.setDescription(examDetails.getDescription());
            exam.setDuration(examDetails.getDuration());
            return examRepository.save(exam);
        } else {
            return null;
        }
    }

    // Delete an exam
    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id) {
        examRepository.deleteById(id);
    }
}
