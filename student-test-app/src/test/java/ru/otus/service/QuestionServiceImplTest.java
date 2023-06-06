package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionDao questionDao;
    private QuestionService questionService;


    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl(questionDao);
    }

    @Test
    void shouldGetAllQuestions() {
        List<Answer> answers = new ArrayList<>() {{
            add(new Answer("abc", true));
            add(new Answer("acd", false));
            add(new Answer("bdc", false));
        }};
        Question question = new Question("question", answers);
        Mockito.when(questionDao.getAll()).thenReturn(List.of(question));
        Assertions.assertNotNull(questionService.getAllQuestion());
    }

    @Test
    void shouldThrowException() {
        Mockito.when(questionDao.getAll()).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class, () -> questionService.getAllQuestion());
    }

    @Test
    void shouldGetEmptyList() {
        Mockito.when(questionDao.getAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(Collections.emptyList(), questionService.getAllQuestion());
    }
}