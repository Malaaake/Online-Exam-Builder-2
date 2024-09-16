package pfe.exambuilder.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pfe.exambuilder.model.Exam;


@Repository
public interface ExamRepository extends JpaRepository<Exam  , Long> {
    List<Exam> findByCreatorId(Long creatorId);
    List<Exam> findByTitleContaining(String title);
}
