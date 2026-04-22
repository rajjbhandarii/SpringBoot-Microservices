package com.king.SpringSecurity.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.king.SpringSecurity.Feign.QuestionServiceInterface;
import com.king.SpringSecurity.Model.QuestionDto;
import com.king.SpringSecurity.Model.QuestionWrapper;

@Service
public class RequestQuestionService {

    private final QuestionServiceInterface requQuestionServiceInterface;

    public RequestQuestionService(QuestionServiceInterface questionService) {
        this.requQuestionServiceInterface = questionService;
    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(
            String categoryName,
            Integer numQuestions) {
        return requQuestionServiceInterface.generateQuestionsForQuiz(categoryName, numQuestions);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionFormId(List<Integer> questionIds) {
        return requQuestionServiceInterface.getQuizQuestionFormId(questionIds);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionByDifficulty(String difficulty) {
        return requQuestionServiceInterface.getQuestionByDifficulty(difficulty);
    }

    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        return requQuestionServiceInterface.getAllQuestions();
    }

    public ResponseEntity<List<QuestionDto>> getQuestionsByCategory(String category) {
        return requQuestionServiceInterface.getQuestionsByCategory(category);
    }

    public ResponseEntity<List<QuestionDto>> getQuestionsByRightAnswer(String answer) {
        return requQuestionServiceInterface.getQuestionsByRightAnswer(answer);
    }

    public ResponseEntity<String> addQuestion(QuestionDto question) {
        return requQuestionServiceInterface.addQuestion(question);
    }
}
