package com.king.SpringSecurity.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.king.SpringSecurity.Model.QuestionWrapper;
import com.king.SpringSecurity.Service.RequestQuestionService;
import com.king.SpringSecurity.Model.Question;

@RestController
@RequestMapping("admin")
public class QuestionRequestController {

    private final RequestQuestionService questionService;

    public QuestionRequestController(RequestQuestionService QuestionService) {
        this.questionService = QuestionService;
    }

    @GetMapping("question/getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("question/byCategory/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("question/byRightAnswer/{answer}")
    public ResponseEntity<List<Question>> getQuestionsByRightAnswer(@PathVariable String answer) {
        return questionService.getQuestionsByRightAnswer(answer);
    }

    @PostMapping("question/addQuestions")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("question/createQuiz")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(
            @RequestParam String categoryName,
            @RequestParam Integer numQuestions) {
        return questionService.generateQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("question/byQuestionId")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionFormId(@RequestBody List<Integer> questionIds) {
        return questionService.getQuizQuestionFormId(questionIds);
    }

    @GetMapping("question/byDifficulty/{difficulty}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionByDifficulty(@PathVariable String difficulty) {
        return questionService.getQuestionByDifficulty(difficulty);
    }

}
