package com.skypro.Exam.repository.interfaces;

import com.skypro.Exam.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
}
