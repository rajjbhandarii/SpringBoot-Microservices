package com.king.quiz_service.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.king.quiz_service.Model.QuestionWrapper;
import com.king.quiz_service.Model.QuizDto;
import com.king.quiz_service.Model.Submission;
import com.king.quiz_service.Service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getQuizNumber(), quizDto.getTitle());
    }

    @GetMapping("get/{quizNumber}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer quizNumber) {
        return quizService.getQuiz(quizNumber);
    }

    @PostMapping("submit/{quizNumber}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizNumber, @RequestBody List<Submission> answers) {
        return quizService.calculateScore(quizNumber, answers);
    }

    @GetMapping("difficulty/{difficulty}")
    public ResponseEntity<List<QuestionWrapper>> getQuizByDifficulty(@PathVariable String difficulty) {
        return quizService.getQuestionByDifficulty(difficulty);
    }

}
