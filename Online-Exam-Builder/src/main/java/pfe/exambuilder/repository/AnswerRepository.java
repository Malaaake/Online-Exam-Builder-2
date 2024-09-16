package pfe.exambuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pfe.exambuilder.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
