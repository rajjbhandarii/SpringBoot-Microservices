package com.king.quiz_service.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.king.quiz_service.Feign.QuizInterface;
import com.king.quiz_service.Model.QuestionWrapper;
import com.king.quiz_service.Model.Quiz;
import com.king.quiz_service.Model.Submission;
import com.king.quiz_service.Repo.QuizDao;

@Service
public class QuizService {

    private final QuizDao quizDao;
    private final QuizInterface quizInterface;

    public QuizService(QuizDao quizDao, QuizInterface quizInterface) {
        this.quizDao = quizDao;
        this.quizInterface = quizInterface;

    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {

            List<Integer> questions = quizInterface.generateQuestionsForQuiz(category, numQ).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionsIds(questions);
            quizDao.save(quiz);

            return ResponseEntity.ok("Quiz created with " + questions.size() + " questions.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error creating quiz.");
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer quizNumber) {
        try {
            List<QuestionWrapper> questionForUser = quizInterface
                    .getQuizQuestionFormId(quizDao.findById(quizNumber).get().getQuestionsIds()).getBody();
            return ResponseEntity.ok(questionForUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    public ResponseEntity<Integer> calculateScore(Integer quizNumber, List<Submission> responses) {
        try {
            Integer score = quizInterface.getScore(responses).getBody();
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>((Integer) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionByDifficulty(String difficulty) {
        try {
            List<QuestionWrapper> questionForUser = quizInterface.getQuestionByDifficulty(difficulty).getBody();
            return ResponseEntity.ok(questionForUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
