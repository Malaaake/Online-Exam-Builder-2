package pfe.exambuilder.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pfe.exambuilder.model.Exam;
import pfe.exambuilder.repository.ExamRepository;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    
    
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam createExam(Exam exam) {
        exam.setCreatedAt(LocalDateTime.now());
        return examRepository.save(exam);
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id)
            .orElseThrow();
    }

    
    
    public Exam updateExam(Long id, Exam examDetails) {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isPresent()) {
            Exam existingExam = exam.get();
            existingExam.setTitle(examDetails.getTitle());
            existingExam.setDescription(examDetails.getDescription());
            existingExam.setDuration(examDetails.getDuration());
            existingExam.setTotalMarks(examDetails.getTotalMarks());
            // Update other fields as necessary
            return examRepository.save(existingExam);
        }
        return null; // Or throw an exception
    }
    
    
    
  /*  public Exam updateExam(Long id, Exam examDetails) {
        Exam exam = getExamById(id);
        exam.setTitle(examDetails.getTitle());
        exam.setDescription(examDetails.getDescription());
        return examRepository.save(exam);
    }
    
    */

  public void deleteExam(Long id) {
    
     Exam exam = examRepository.findById(id)
                .orElseThrow();
            examRepository.delete(exam);
  }


}
