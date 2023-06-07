package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.converter.QuestionConverter;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.utils.Printer;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RunnerServiceImplTest {
    public static final String STRING = "question\n\tabc\n\tacd\n\tbdc";

    @Mock
    private QuestionService questionService;

    @Mock
    private Printer printer;

    @Mock
    private QuestionConverter converter;

    private RunnerService service;

    private Question question;

    private List<Question> questions;


    @BeforeEach
    void setUp() {
        service = new RunnerServiceImpl(questionService, printer, converter);
        List<Answer> answers = new ArrayList<>() {{
            add(new Answer("abc", true));
            add(new Answer("acd", false));
            add(new Answer("bdc", false));
        }};
        question = new Question("question", answers);
        questions = List.of(question);

    }

    @Test
    void printAnswers() {
        Mockito.when(questionService.getAllQuestion()).thenReturn(questions);
        Mockito.when(converter.convertToString(question)).thenReturn(STRING);
        service.printQuestions();
        Mockito.verify(questionService).getAllQuestion();
        Mockito.verify(converter).convertToString(question);
        Mockito.verify(printer).print(STRING);
    }
}