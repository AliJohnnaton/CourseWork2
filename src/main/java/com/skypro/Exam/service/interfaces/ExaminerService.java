package com.skypro.Exam.service.interfaces;

import com.skypro.Exam.model.Question;

import java.util.Set;

public interface ExaminerService {
    Set<Question> getQuestions(int count);
}
