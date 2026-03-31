package com.king.SpringSecurity.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.king.SpringSecurity.Model.QuestionWrapper;
import com.king.SpringSecurity.Model.QuizDto;
import com.king.SpringSecurity.Model.Submission;

@FeignClient("QUIZ-SERVICE")
public interface QuizServiceInterface {
    @PostMapping("quiz/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto);

    @GetMapping("quiz/get/{quizNumber}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer quizNumber);

    @PostMapping("quiz/submit/{quizNumber}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizNumber, @RequestBody List<Submission> answers);

    @GetMapping("quiz/difficulty/{difficulty}")
    public ResponseEntity<List<QuestionWrapper>> getQuizByDifficulty(@PathVariable String difficulty);
}
