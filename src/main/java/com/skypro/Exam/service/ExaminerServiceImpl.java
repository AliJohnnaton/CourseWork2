package com.skypro.Exam.service;

import com.skypro.Exam.model.Question;
import com.skypro.Exam.service.interfaces.ExaminerService;
import com.skypro.Exam.service.interfaces.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Set<QuestionService> questionServices;

    public ExaminerServiceImpl(Set<QuestionService> questionServices) {
        this.questionServices = questionServices;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        int totalAvailable = questionServices.stream()
                .mapToInt(service -> {
                    try {
                        return service.getAll().size();
                    } catch (Exception e) {
                        return 0;
                    }
                })
                .sum();

        if (amount > totalAvailable) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Вы первысили кол-во возможных вопросов"
            );
        }

        Set<Question> uniqueQuestions = new HashSet<>();
        while (uniqueQuestions.size() < amount) {
            for (QuestionService service : questionServices) {
                if (uniqueQuestions.size() >= amount) break;
                try {
                    uniqueQuestions.add(service.getRandomQuestion());
                } catch (Exception ignored) {
                }
            }
        }
        return uniqueQuestions;
    }
}
