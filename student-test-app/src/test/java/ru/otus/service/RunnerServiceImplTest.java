package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.Question;
import ru.otus.utils.ConsolePrinter;

@ExtendWith(MockitoExtension.class)
class RunnerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @Mock
    private ConsolePrinter printer;

    private RunnerService service;

    @BeforeEach
    void setUp() {
        service = new RunnerServiceImpl(questionService, printer);
    }

    @Test
    void printAnswers() {
        Question question = new Question("question", "1", "2", "3", "result");
        service.printAnswers();
        Mockito.verify(questionService, Mockito.times(1)).getAllQuestion();
        Mockito.verify(printer, Mockito.times(0)).printQuestion(question);
    }
}