package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.domain.Result;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@DisplayName("Сервсис тестирования")
@SpringBootTest
class TestingServiceImplTest {
    @MockBean
    private IOService ioService;
    @MockBean
    private QuestionService questionService;
    @MockBean
    private ConversionService conversionService;
    @Autowired
    private TestingServiceImpl service;
    private List<Question> questions;

    @BeforeEach
    void setUp() {
        List<Answer> answers = List.of(
                new Answer("a1", false),
                new Answer("a2", true),
                new Answer("a3", false)
        );
        List<Answer> answers2 = List.of(
                new Answer("a1", false),
                new Answer("a2", true),
                new Answer("a3", false)
        );
        List<Answer> answers3 = List.of(
                new Answer("a1", false),
                new Answer("a2", true),
                new Answer("a3", false)
        );
        List<Answer> answers4 = List.of(
                new Answer("a1", false),
                new Answer("a2", false),
                new Answer("a3", true)
        );
        List<Answer> answers5 = List.of(
                new Answer("a1", false),
                new Answer("a2", true),
                new Answer("a3", false)
        );

        questions = List.of(
                new Question("question1", answers),
                new Question("question2", answers2),
                new Question("question3", answers3),
                new Question("question4", answers4),
                new Question("question5", answers5)
        );
    }

    @Test
    @DisplayName("должен возвращать результат тестирования")
    void shouldReturnExpectedResult() {
        String question = "any string";
        when(questionService.getAllQuestion()).thenReturn(questions);
        when(conversionService.convert(any(Question.class), eq(String.class))).thenReturn(question);
        when(ioService.readIntWithPrompt(question)).thenReturn(2);
        Result expected = Result.builder().pass(4).total(5).build();
        Result actual = service.testing();
        assertEquals(expected, actual);
    }
}