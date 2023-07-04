package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
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
    private MessageSourceService messageService;

    @Mock
    private IOService ioService;

    @Mock
    private CheckResultService checkService;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private ApplicationRunner applicationRunner;

    @Test
    @DisplayName("должен распечатать результат")
    void shouldPrintResult() {
        String fullName = "John Smith";
        String message = "something message";
        User user = User.builder().name("John").surname("Smith").build();
        Result result = Result.builder().pass(4).total(5).user(user).build();
        String expectedResult = "4/5";

        when(ioService.readStringWithPrompt(anyString())).thenReturn(fullName);
        when(messageService.getMessage(anyString())).thenReturn(message);
        when(conversionService.convert(fullName, User.class)).thenReturn(user);
        when(testingService.testing()).thenReturn(result);
        when(conversionService.convert(result, String.class)).thenReturn(expectedResult);
        when(checkService.check(result)).thenReturn(true);

        applicationRunner.run();

        verify(ioService, times(1)).readStringWithPrompt(anyString());
        verify(testingService, times(1)).testing();
        verify(ioService, times(1)).outputString(anyString());
        verify(messageService, times(2)).getMessage(anyString());
        verify(messageService, times(1)).getMessage(anyString(), any());
        verify(conversionService, times(1)).convert(result, String.class);
        verify(checkService, times(1)).check(result);
        assertEquals(result.getUser(), user);
    }
}