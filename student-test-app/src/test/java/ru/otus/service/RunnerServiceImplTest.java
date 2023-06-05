package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dto.QuestionDto;
import ru.otus.utils.ConsolePrinterImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class RunnerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @Mock
    private ConsolePrinterImpl printer;

    private RunnerService service;

    @BeforeEach
    void setUp() {
        service = new RunnerServiceImpl(questionService, printer);
    }

    @Test
    void printAnswers() {
        QuestionDto questionDto = new QuestionDto("question", List.of("1", "2", "3"), 2);
        service.printQuestions();
        Mockito.verify(questionService, Mockito.times(1)).getAllQuestion();
        Mockito.verify(printer, Mockito.times(0)).print(questionDto.getQuestion(), questionDto.getAnswers());
    }
}