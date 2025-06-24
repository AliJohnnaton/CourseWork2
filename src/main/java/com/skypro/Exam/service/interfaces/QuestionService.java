package com.skypro.Exam.service.interfaces;

import com.skypro.Exam.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
