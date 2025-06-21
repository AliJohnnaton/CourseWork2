package com.skypro.Exam.service;

import com.skypro.Exam.model.Question;
import com.skypro.Exam.service.interfaces.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Random;

@Service("mathQuestionService")
public class MathQuestionService implements QuestionService {
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                "Ручное добавление отключено для математических вопросов.");
    }

    @Override
    public Question remove(Question question) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                "Удаление отключено для математических вопросов");
    }

    @Override
    public Collection<Question> getAll() {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                "GetAll отключен для математических вопросов");
    }

    @Override
    public Question getRandomQuestion() {
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        String question;
        String answer;
        if(a>b){
            question = a + " - " + b + " = ?";
            answer = String.valueOf(a - b);
        }
        else{
            question = a + " + " + b + " = ?";
            answer = String.valueOf(a + b);
        }
        return new Question(question, answer);
    }
}
