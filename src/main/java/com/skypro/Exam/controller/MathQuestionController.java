package com.skypro.Exam.controller;

import com.skypro.Exam.model.Question;
import com.skypro.Exam.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {
    private final QuestionService questionService;

    public MathQuestionController(@Qualifier("math") QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/random")
    public Question getRandomQuestion() {
        return questionService.getRandomQuestion();
    }
}
