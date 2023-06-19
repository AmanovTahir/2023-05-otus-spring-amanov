package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
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
    private IOService ioService;

    @Mock
    private UserService userService;

    @Mock
    private CheckResultService checkService;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private ApplicationRunner applicationRunner;

    @BeforeEach
    void setUp() {
        applicationRunner = new ApplicationRunner(testingService, ioService, userService,
                conversionService, checkService);
    }

    @Test
    @DisplayName("должен распечатать результат")
    void shouldPrintResult() {
        String fullName = "John Smith";
        User user = User.builder().name("John").surname("Smith").build();
        Result result = Result.builder().pass(4).total(5).build();
        String expectedResult = "4/5";

        when(ioService.readStringWithPrompt(anyString())).thenReturn(fullName);
        when(userService.getUser(fullName)).thenReturn(user);
        when(testingService.testing()).thenReturn(result);
        when(conversionService.convert(result, String.class)).thenReturn(expectedResult);
        when(checkService.check(result)).thenReturn(true);

        applicationRunner.run();

        verify(ioService, times(1)).readStringWithPrompt(anyString());
        verify(userService, times(1)).getUser(fullName);
        verify(testingService, times(1)).testing();
        verify(ioService, times(2)).outputString(anyString());
        verify(conversionService, times(1)).convert(result, String.class);
        verify(checkService, times(1)).check(result);
        assertEquals(user.getResult(), result);
    }
}