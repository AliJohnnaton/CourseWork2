package com.skypro.Exam.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class MathQuestionServiceTest {
    private final MathQuestionService service = new MathQuestionService();

    @Test
    void testGetRandomQuestion() {
        var question = service.getRandomQuestion();
        assertTrue(question.getQuestion().contains(" + "));
        assertDoesNotThrow(() -> Integer.parseInt(question.getAnswer()));
    }

    @Test
    void testAddThrowsException() {
        assertThrows(ResponseStatusException.class,
                () -> service.add("Test", "Test"));
    }
}
