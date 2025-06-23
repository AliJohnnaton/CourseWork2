package com.skypro.Exam.controller;

import com.skypro.Exam.model.Question;
import com.skypro.Exam.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(@Qualifier("java") QuestionService questionService) {
        this.questionService = questionService;
    }
    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question,@RequestParam String answer){
        return questionService.add(question,answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question,@RequestParam String answer){
        return questionService.remove(new Question(question,answer));
    }

    @GetMapping
    public Collection<Question> getAllQuestions(){
        return questionService.getAll();
    }
}
