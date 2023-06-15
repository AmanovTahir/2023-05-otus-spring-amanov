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
import ru.otus.domain.Messages;
import ru.otus.domain.Result;
import ru.otus.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@DisplayName("Сервис запуска приложения")
@ExtendWith(MockitoExtension.class)
class ApplicationRunnerTest {
    @Mock
    private TestingService testingService;

    @Mock
    private IOService ioService;

    @Mock
    private UserService userService;

    @Mock
    private Converter<Result, String> resultConverter;

    @Mock
    private CheckResultService checkService;

    @InjectMocks
    private ApplicationRunner applicationRunner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        applicationRunner = new ApplicationRunner(testingService, ioService, userService, resultConverter, checkService);
    }

    @Test
    @DisplayName("должен распечатать результат")
    void shouldPrintResult() {
        String fullName = "John Smith";
        User user = User.builder().name("John").surname("Smith").build();
        Result result = Result.builder().pass(4).total(5).build();
        String expectedResult = "4/5";

        when(ioService.readStringWithPrompt(Messages.HELLO.getMessage())).thenReturn(fullName);
        when(userService.getUser(fullName)).thenReturn(user);
        when(testingService.testing()).thenReturn(result);
        when(resultConverter.convert(result)).thenReturn(expectedResult);
        when(checkService.check(result)).thenReturn(true);

        applicationRunner.run();

        verify(ioService, times(1)).readStringWithPrompt(anyString());
        verify(userService, times(1)).getUser(fullName);
        verify(testingService, times(1)).testing();
        verify(ioService, times(2)).outputString(anyString());
        verify(resultConverter, times(1)).convert(result);
        verify(checkService, times(1)).check(result);
        assertEquals(user.getResult(), result);
    }
}