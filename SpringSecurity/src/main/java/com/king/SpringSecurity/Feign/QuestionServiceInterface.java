package com.king.SpringSecurity.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.king.SpringSecurity.Model.QuestionWrapper;
import com.king.SpringSecurity.Model.Submission;

@FeignClient("QUESTION-SERVICE")
public interface QuestionServiceInterface {

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(
            @RequestParam String categoryName,
            @RequestParam Integer numQuestions);

    @PostMapping("getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionFormId(@RequestBody List<Integer> questionIds);

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Submission> submissions);

    @GetMapping("difficultyLevel/{difficulty}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionByDifficulty(@PathVariable String difficulty);
}
