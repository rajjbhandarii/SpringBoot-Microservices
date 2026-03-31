package com.king.SpringSecurity.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.king.SpringSecurity.Feign.QuestionServiceInterface;
import com.king.SpringSecurity.Model.QuestionWrapper;
import com.king.SpringSecurity.Model.Submission;

@Service
public class RequestQuestionService {
    private final QuestionServiceInterface questionService;

    public RequestQuestionService(QuestionServiceInterface questionService) {
        this.questionService = questionService;
    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(
            String categoryName,
            Integer numQuestions) {
        return questionService.generateQuestionsForQuiz(categoryName, numQuestions);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionFormId(List<Integer> questionIds) {
        return questionService.getQuizQuestionFormId(questionIds);
    }

    public ResponseEntity<Integer> getScore(List<Submission> submissions) {
        return questionService.getScore(submissions);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionByDifficulty(String difficulty) {
        return questionService.getQuestionByDifficulty(difficulty);
    }
}
