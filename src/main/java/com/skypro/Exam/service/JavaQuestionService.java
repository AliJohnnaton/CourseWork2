package com.skypro.Exam.service;

import com.skypro.Exam.model.Question;
import com.skypro.Exam.repository.interfaces.QuestionRepository;
import com.skypro.Exam.service.interfaces.QuestionService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Primary
@Service("javaQuestionService")
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository repository;
    private final Random random = new Random();

    public JavaQuestionService(QuestionRepository repository) {
        this.repository = repository;
        repository.add(new Question("Что такое Java?", "Язык программирования"));
        repository.add(new Question("Что такое JVM?", "Виртуальная машина Java"));
        repository.add(new Question("Что такое ООП?", "Парадигма программирования"));
        repository.add(new Question("Что такое ArrayList?", "Реализация динамического массива в Java"));
    }

    @Override
    public Question add(String question, String answer) {
        return repository.add(new Question(question, answer));
    }

    @Override
    public Question remove(Question question) {
        return repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> questions = repository.getAll();
        if (questions.isEmpty()) {
            throw new IllegalStateException("Нет доступных вопросов");
        }
        int index = random.nextInt(questions.size());
        return questions.stream()
                .skip(index)
                .findFirst()
                .orElseThrow();
    }
}