package com.king.SpringSecurity.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.king.SpringSecurity.Model.QuestionWrapper;
import com.king.SpringSecurity.Model.QuizDto;
import com.king.SpringSecurity.Model.Submission;
import com.king.SpringSecurity.Service.RequestQuizService;

@RestController
@RequestMapping("user")
public class quizRequestController {

    private final RequestQuizService requestQuizService;

    public quizRequestController(RequestQuizService requestQuizService) {
        this.requestQuizService = requestQuizService;
    }

    @PostMapping("quiz/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return requestQuizService.createQuizFromQuizService(quizDto);
    }

    @GetMapping("quiz/get/{quizNumber}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer quizNumber) {
        return requestQuizService.getQuizFromQuizService(quizNumber);
    }

    @PostMapping("quiz/submit/{quizNumber}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizNumber, @RequestBody List<Submission> answers) {
        return requestQuizService.submitQuizToQuizService(quizNumber, answers);
    }

    @GetMapping("quiz/difficulty/{difficulty}")
    public ResponseEntity<List<QuestionWrapper>> getQuizByDifficulty(@PathVariable String difficulty) {
        return requestQuizService.getQuizByDifficultyFromQuizService(difficulty);
    }

}
