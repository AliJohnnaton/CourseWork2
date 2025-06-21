package com.skypro.Exam.service;

import com.skypro.Exam.model.Question;
import com.skypro.Exam.repository.interfaces.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExaminerServiceImplTest {
    private final Question javaQuestion1 = new Question("Q1", "A1");
    private final Question javaQuestion2 = new Question("Q2", "A2");
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        JavaQuestionService javaService = new JavaQuestionService(new TestQuestionRepository());
        MathQuestionService mathService = new MathQuestionService();
        javaService.add(javaQuestion1.getQuestion(), javaQuestion1.getAnswer());
        javaService.add(javaQuestion2.getQuestion(), javaQuestion2.getAnswer());
        examinerService = new ExaminerServiceImpl(Set.of(javaService, mathService));
    }

    @Test
    void getQuestions_shouldReturnCorrectAmount() {
        Set<Question> result = (Set<Question>) examinerService.getQuestions(2);
        assertEquals(2, result.size());
    }

    @Test
    void getQuestions_shouldThrowWhenNotEnoughQuestions() {
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(3));
    }

    @Test
    void getQuestions_shouldReturnMixedQuestions() {
        Set<Question> result = (Set<Question>) examinerService.getQuestions(2);
        assertTrue(result.stream().anyMatch(q ->
                q.getQuestion().equals(javaQuestion1.getQuestion()) ||
                        q.getQuestion().equals(javaQuestion2.getQuestion())));
        assertTrue(result.stream().anyMatch(q -> q.getQuestion().matches("^\\d.+")));
    }

    private static class TestQuestionRepository implements QuestionRepository {
        private final Set<Question> questions = new HashSet<>();

        @Override
        public Question add(Question question) {
            questions.add(question);
            return question;
        }

        @Override
        public Question remove(Question question) {
            questions.remove(question);
            return question;
        }

        @Override
        public Collection<Question> getAll() {
            return new HashSet<>(questions);
        }
    }
}
