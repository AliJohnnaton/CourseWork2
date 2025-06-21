package com.skypro.Exam.service;

import com.skypro.Exam.model.Question;
import com.skypro.Exam.repository.interfaces.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService service;
    private List<Question> fakeStorage;

    @BeforeEach
    void setUp() {
        fakeStorage = new ArrayList<>();
        QuestionRepository repository = new QuestionRepository() {
            @Override
            public Question add(Question question) {
                fakeStorage.add(question);
                return question;
            }

            @Override
            public Question remove(Question question) {
                if (fakeStorage.remove(question)) {
                    return question;
                }
                return null;
            }

            @Override
            public Collection<Question> getAll() {
                return new ArrayList<>(fakeStorage);
            }
        };

        service = new JavaQuestionService(repository);
    }

    @Test
    void add_shouldAddQuestionToRepository() {
        String questionText = "Что такое Java?";
        String answer = "Язык программирования";

        Question result = service.add(questionText, answer);

        assertEquals(questionText, result.getQuestion());
        assertEquals(answer, result.getAnswer());
        assertTrue(fakeStorage.contains(result));
    }

    @Test
    void remove_shouldDeleteQuestionFromRepository() {
        Question question = new Question("Что такое ООП?", "Парадигма");
        fakeStorage.add(question);

        Question removed = service.remove(question);

        assertEquals(question, removed);
        assertFalse(fakeStorage.contains(question));
    }

    @Test
    void getAll_shouldReturnAllQuestions() {
        Question q1 = new Question("Q1", "A1");
        Question q2 = new Question("Q2", "A2");
        fakeStorage.addAll(List.of(q1, q2));

        Collection<Question> result = service.getAll();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of(q1, q2)));
    }

    @Test
    void getRandomQuestion_shouldReturnQuestion() {
        Question question = new Question("Q", "A");
        fakeStorage.add(question);

        Question result = service.getRandomQuestion();

        assertEquals(question, result);
    }

    @Test
    void getRandomQuestion_shouldThrowWhenNoQuestions() {
        fakeStorage.clear();

        assertThrows(IllegalStateException.class, () -> service.getRandomQuestion());
    }
}
