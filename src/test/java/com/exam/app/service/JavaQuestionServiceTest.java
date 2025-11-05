package com.exam.app.service;

import com.exam.app.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Test
    void testAddQuestion() {
        Question question = new Question("What is Java?", "Programming language");
        
        Question result = javaQuestionService.add(question);
        
        assertEquals(question, result);
        assertTrue(javaQuestionService.getAll().contains(question));
    }

    @Test
    void testAddQuestionByParams() {
        Question result = javaQuestionService.add("What is OOP?", "Object Oriented Programming");
        
        assertEquals("What is OOP?", result.getQuestion());
        assertEquals("Object Oriented Programming", result.getAnswer());
    }

    @Test
    void testRemoveQuestion() {
        Question question = new Question("Test", "Answer");
        javaQuestionService.add(question);
        
        Question removed = javaQuestionService.remove(question);
        
        assertEquals(question, removed);
        assertFalse(javaQuestionService.getAll().contains(question));
    }

    @Test
    void testGetAllQuestions() {
        Question q1 = javaQuestionService.add("Q1", "A1");
        Question q2 = javaQuestionService.add("Q2", "A2");
        
        Collection<Question> allQuestions = javaQuestionService.getAll();
        
        assertEquals(2, allQuestions.size());
        assertTrue(allQuestions.contains(q1));
        assertTrue(allQuestions.contains(q2));
    }

    @Test
    void testGetRandomQuestion() {
        javaQuestionService.add("Q1", "A1");
        javaQuestionService.add("Q2", "A2");
        
        Question randomQuestion = javaQuestionService.getRandomQuestion();
        
        assertNotNull(randomQuestion);
        assertTrue(randomQuestion.getQuestion().startsWith("Q"));
    }
}
