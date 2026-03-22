package com.king.quiz_service.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.king.quiz_service.Model.QuestionWrapper;
import com.king.quiz_service.Model.Submission;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(
            @RequestParam String categoryName,
            @RequestParam Integer numQuestions);

    @PostMapping("question/getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionFormId(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Submission> submissions);
}
