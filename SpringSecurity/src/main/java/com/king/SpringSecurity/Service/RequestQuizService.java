package com.king.SpringSecurity.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.king.SpringSecurity.Feign.SecurityInterface;
import com.king.SpringSecurity.Model.QuestionWrapper;
import com.king.SpringSecurity.Model.QuizDto;
import com.king.SpringSecurity.Model.Submission;

@Service
public class RequestQuizService {
    private final SecurityInterface securityInterface;

    public RequestQuizService(SecurityInterface securityInterface) {
        this.securityInterface = securityInterface;
    }

    public ResponseEntity<String> createQuizFromQuizService(QuizDto quizDto) {
        return securityInterface.createQuiz(quizDto);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizFromQuizService(Integer quizNumber) {
        return securityInterface.getQuiz(quizNumber);
    }

    public ResponseEntity<Integer> submitQuizToQuizService(Integer quizNumber, List<Submission> answers) {
        return securityInterface.submitQuiz(quizNumber, answers);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizByDifficultyFromQuizService(String difficulty) {
        return securityInterface.getQuizByDifficulty(difficulty);
    }
}
