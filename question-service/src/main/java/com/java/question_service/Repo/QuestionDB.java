package com.java.question_service.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.question_service.Model.Question;

@Repository
public interface QuestionDB extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    List<Question> findAllByRightAnswer(String rightAnswer);

    @Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findrandomQuestionsByCategory(String category, Integer numQ);

}
