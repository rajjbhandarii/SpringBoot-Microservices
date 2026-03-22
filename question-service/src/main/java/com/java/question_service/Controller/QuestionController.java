package com.java.question_service.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.question_service.Model.Question;
import com.java.question_service.Model.QuestionWrapper;
import com.java.question_service.Model.Submission;
import com.java.question_service.Service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("rightAnswer/{answer}")
    public ResponseEntity<List<Question>> getQuestionsByRightAnswer(@PathVariable String answer) {
        return questionService.getQuestionsByRightAnswer(answer);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // genarate
    // getQuestion(questionid)
    // getScore

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(
            @RequestParam String categoryName,
            @RequestParam Integer numQuestions) {
        return questionService.generateQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionFormId(@RequestBody List<Integer> questionIds) {
        return questionService.getQuizQuestionFormId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Submission> submissions) {
        return questionService.getScore(submissions);
    }

}
