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
import ru.otus.utils.ConsolePrinterImpl;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RunnerServiceImplTest {
    @Mock
    private QuestionService questionService;
    @Mock
    private ConsolePrinterImpl printer;
    @Mock
    private QuestionConverter converter;

    private RunnerService service;

    @BeforeEach
    void setUp() {
        service = new RunnerServiceImpl(questionService, printer, converter);
    }

    @Test
    void printAnswers() {
        List<Answer> answers = new ArrayList<>() {{
            add(new Answer("abc", true));
            add(new Answer("acd", false));
            add(new Answer("bdc", false));
        }};
        Question question = new Question("question", answers);
        service.printQuestions();
        Mockito.verify(questionService, Mockito.times(1)).getAllQuestion();
        Mockito.verify(converter, Mockito.times(1)).convertToString(question);
        Mockito.verify(printer, Mockito.times(1)).print(question.getQuestion());
    }
}