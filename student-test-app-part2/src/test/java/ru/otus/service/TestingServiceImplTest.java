package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
@ExtendWith(MockitoExtension.class)
class TestingServiceImplTest {
    @Mock
    private IOService ioService;
    @Mock
    private QuestionService questionService;
    @Mock
    private ConversionService conversionService;
    @InjectMocks
    private TestingServiceImpl service;

    private List<Question> questions;


    @BeforeEach
    void setUp() {
        service = new TestingServiceImpl(ioService, questionService, conversionService);
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
        when(ioService.readIntWithPrompt(question)).thenReturn(1);
        Result expected = Result.builder().pass(0).total(5).build();
        Result actual = service.testing();
        assertEquals(expected, actual);
    }
}