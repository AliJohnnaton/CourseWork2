package com.skypro.Exam.controller;

import com.skypro.Exam.model.Question;
import com.skypro.Exam.service.interfaces.ExaminerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{count}")
    public Collection<Question> getQuestions(@PathVariable int count) {
        return examinerService.getQuestions(count);
    }
}
