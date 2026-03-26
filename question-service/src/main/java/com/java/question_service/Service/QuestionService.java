package com.java.question_service.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.java.question_service.Model.Question;
import com.java.question_service.Model.QuestionWrapper;
import com.java.question_service.Model.Submission;
import com.java.question_service.Repo.QuestionDB;

@Service
public class QuestionService {

    private final QuestionDB questionDb;

    public QuestionService(QuestionDB questionDb) {
        this.questionDb = questionDb;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return ResponseEntity.ok(questionDb.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return ResponseEntity.ok(questionDb.findByCategory(category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByRightAnswer(String rightAnswer) {
        try {
            return ResponseEntity.ok(questionDb.findAllByRightAnswer(rightAnswer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            // Always let the database generate the primary key for new rows.
            question.setId(null);
            questionDb.save(question);
            return ResponseEntity.status(HttpStatus.CREATED).body("Question added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add question");
        }
    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String categoryName, Integer numQuestions) {
        try {
            List<Integer> questions = questionDb.findrandomQuestionsByCategory(categoryName, numQuestions);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionFormId(List<Integer> questionIds) {
        try {
            List<QuestionWrapper> wrapper = new ArrayList<>();
            List<Question> questions = new ArrayList<>();

            questions.addAll(questionDb.findAllById(questionIds));

            for (Question question : questions) {
                QuestionWrapper wrapper1 = new QuestionWrapper();
                wrapper1.setId(question.getId());
                wrapper1.setQuestionTitle(question.getQuestionTitle());
                wrapper1.setOption1(question.getOption1());
                wrapper1.setOption2(question.getOption2());
                wrapper1.setOption3(question.getOption3());
                wrapper1.setOption4(question.getOption4());
                wrapper.add(wrapper1);
            }
            return new ResponseEntity<>(wrapper, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getScore(List<Submission> responses) {
        try {
            int score = 0;
            for (Submission res : responses) {
                Question question = questionDb.findById(res.getId()).get();
                if (res.getResponseAnswer().equals(question.getRightAnswer())) {
                    score++;
                }
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>((Integer) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionByDifficulty(String difficulty) {
        try {
            List<Integer> questionIds = questionDb.findAllByDifficultylevel(difficulty);
            getQuizQuestionFormId(questionIds);
            return getQuizQuestionFormId(questionIds);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
