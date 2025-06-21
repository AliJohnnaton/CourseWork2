package com.skypro.Exam.service.interfaces;

import com.skypro.Exam.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int count);
}
