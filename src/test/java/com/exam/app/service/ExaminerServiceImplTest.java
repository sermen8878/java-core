package com.exam.app.service;

import com.exam.app.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void testGetQuestions() {
        Set<Question> mockQuestions = new HashSet<>();
        mockQuestions.add(new Question("Q1", "A1"));
        mockQuestions.add(new Question("Q2", "A2"));
        mockQuestions.add(new Question("Q3", "A3"));

        when(javaQuestionService.getAll()).thenReturn(mockQuestions);
        when(javaQuestionService.getRandomQuestion())
                .thenReturn(new Question("Q1", "A1"))
                .thenReturn(new Question("Q2", "A2"));

        var result = examinerService.getQuestions(2);

        assertEquals(2, result.size());
        verify(javaQuestionService, times(2)).getRandomQuestion();
    }

    @Test
    void testGetQuestionsExceedingLimit() {
        Set<Question> mockQuestions = new HashSet<>();
        mockQuestions.add(new Question("Q1", "A1"));

        when(javaQuestionService.getAll()).thenReturn(mockQuestions);

        assertThrows(ResponseStatusException.class, () -> {
            examinerService.getQuestions(5);
        });
    }
}
