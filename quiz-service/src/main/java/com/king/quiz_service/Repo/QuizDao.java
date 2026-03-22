package com.king.quiz_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.quiz_service.Model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
