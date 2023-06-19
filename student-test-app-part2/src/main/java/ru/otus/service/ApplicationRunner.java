package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.otus.domain.Result;
import ru.otus.domain.User;

@Service
@RequiredArgsConstructor
public class ApplicationRunner {

    private static final String HELLO = "Hello user! Whats your full name?";

    private static final String YOUR_SCORE = "Your score: ";

    private static final String LOOSE = "We're sorry but you didn't get a passing grade";

    private static final String WIN = "Congratulations, you have received a passing score";


    private final TestingService testingService;

    private final IOService ioService;

    private final UserService userService;

    private final ConversionService conversionService;

    private final CheckResultService checkService;


    public void run() {
        String fullName = ioService.readStringWithPrompt(HELLO);
        User user = userService.getUser(fullName);
        Result result = testingService.testing();
        printResult(user, result);
    }

    private void printResult(User user, Result result) {
        user.setResult(result);
        String convert = conversionService.convert(user.getResult(), String.class);
        ioService.outputString(YOUR_SCORE + convert);
        String resultMessage = checkService.check(result) ? WIN : LOOSE;
        ioService.outputString(resultMessage);
    }
}
