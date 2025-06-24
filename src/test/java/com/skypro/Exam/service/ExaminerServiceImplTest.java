package com.skypro.Exam.service;

import com.skypro.Exam.model.Question;
import com.skypro.Exam.service.interfaces.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    private final Question javaQuestion = new Question("Java Q", "A");
    private final Question mathQuestion = new Question("2 + 2", "4");
    @Mock
    private QuestionService javaService;
    @Mock
    private QuestionService mathService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions_shouldReturnMixedQuestions() {
        when(javaService.getRandomQuestion()).thenReturn(javaQuestion);
        when(mathService.getRandomQuestion()).thenReturn(mathQuestion);

        Set<Question> result = examinerService.getQuestions(2);

        assertEquals(2, result.size());
        assertTrue(result.contains(javaQuestion));
        assertTrue(result.contains(mathQuestion));
    }

    @Test
    void getQuestions_shouldThrowWhenNotEnough() {
        when(javaService.getAll()).thenReturn(Set.of(javaQuestion));
        when(mathService.getAll()).thenThrow(UnsupportedOperationException.class);

        assertThrows(ResponseStatusException.class,
                () -> examinerService.getQuestions(2));
    }
}