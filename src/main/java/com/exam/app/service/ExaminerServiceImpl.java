package com.exam.app.service;

import com.exam.app.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    
    private final JavaQuestionService javaQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestions = javaQuestionService.getAll();
        
        if (amount > allQuestions.size()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                \"Requested amount exceeds available questions\"
            );
        }

        Set<Question> randomQuestions = new HashSet<>();
        while (randomQuestions.size() < amount) {
            randomQuestions.add(javaQuestionService.getRandomQuestion());
        }
        
        return randomQuestions;
    }
}
