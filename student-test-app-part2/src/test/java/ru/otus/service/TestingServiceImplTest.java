package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.converter.Converter;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.domain.Result;
import ru.otus.utils.AnswerListUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Сервсис тестирования")
@ExtendWith(MockitoExtension.class)
class TestingServiceImplTest {
    @Mock
    private IOService ioService;
    @Mock
    private QuestionService questionService;
    @Mock
    private AnswerService answerService;
    @Mock
    private Converter<Question, String> questionConverter;
    @Mock
    private AnswerListUtil answerListUtil;
    @InjectMocks
    private TestingServiceImpl service;

    private List<Question> questions;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TestingServiceImpl(ioService, questionService,
                answerService, questionConverter, answerListUtil);
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
        when(questionService.getAllQuestion()).thenReturn(questions);
        Result expected = Result.builder().pass(0).total(5).build();
        Result actual = service.testing();
        assertEquals(expected, actual);
    }
}