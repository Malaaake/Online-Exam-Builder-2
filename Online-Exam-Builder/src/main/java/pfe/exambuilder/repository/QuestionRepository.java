package pfe.exambuilder.repository;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    List<Question> findByExamId(Long examId);

    @Query(value = "SELECT * FROM questions WHERE exam_id = :examId ORDER BY RAND() LIMIT :count", nativeQuery = true)
    List<Question> findRandomQuestionsByExamId(Long examId, int count);
}