package com.skypro.Exam.service;

import com.skypro.Exam.model.Question;
import com.skypro.Exam.repository.interfaces.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    private final Question question = new Question("Q", "A");
    @Mock
    private QuestionRepository repository;
    @InjectMocks
    private JavaQuestionService service;

    @Test
    void add_shouldCallRepository() {
        when(repository.add(question)).thenReturn(question);

        Question result = service.add(question.getQuestion(), question.getAnswer());

        assertEquals(question, result);
        verify(repository).add(question);
    }

    @Test
    void remove_shouldCallRepository() {
        when(repository.remove(question)).thenReturn(question);

        Question result = service.remove(question);

        assertEquals(question, result);
        verify(repository).remove(question);
    }

    @Test
    void getRandomQuestion_shouldThrowWhenEmpty() {
        when(repository.getAll()).thenReturn(Collections.emptySet());

        assertThrows(IllegalStateException.class, () -> service.getRandomQuestion());
    }
}
