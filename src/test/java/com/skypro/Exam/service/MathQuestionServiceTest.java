package com.skypro.Exam.service;

import com.skypro.Exam.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {
    @InjectMocks
    private MathQuestionService service;

    @Test
    void getRandomQuestion_shouldReturnValidQuestion() {
        Question question = service.getRandomQuestion();

        assertNotNull(question.getQuestion());
        assertNotNull(question.getAnswer());
    }

    @Test
    void add_shouldThrowException() {
        assertThrows(ResponseStatusException.class,
                () -> service.add("Q", "A"));
    }
}
