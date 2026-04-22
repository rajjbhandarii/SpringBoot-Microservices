package com.king.SpringSecurity.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.king.SpringSecurity.Model.QuestionDto;
import com.king.SpringSecurity.Model.QuestionWrapper;

@FeignClient("QUESTION-SERVICE")
public interface QuestionServiceInterface {

    @GetMapping("question/questions")
    public ResponseEntity<List<QuestionDto>> getAllQuestions();

    @GetMapping("question/category/{category}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByCategory(@PathVariable String category);

    @GetMapping("question/rightAnswer/{answer}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByRightAnswer(@PathVariable String answer);

    @PostMapping("question/add")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionDto question);

    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(
            @RequestParam String categoryName,
            @RequestParam Integer numQuestions);

    @PostMapping("question/getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionFormId(@RequestBody List<Integer> questionIds);

    @GetMapping("question/difficultyLevel/{difficulty}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionByDifficulty(@PathVariable String difficulty);
}
