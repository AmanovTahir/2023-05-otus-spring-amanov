package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.Converter;
import ru.otus.domain.Messages;
import ru.otus.domain.Result;
import ru.otus.domain.User;

@Service
@RequiredArgsConstructor
public class ApplicationRunner {

    private final TestingService testingService;

    private final IOService ioService;

    private final UserService userService;

    private final Converter<Result, String> resultConverter;

    private final CheckResultService checkService;

    public void run() {
        String fullName = ioService.readStringWithPrompt(Messages.HELLO.getMessage());
        User user = userService.getUser(fullName);
        Result result = testingService.testing();
        printResult(user, result);
    }

    private void printResult(User user, Result result) {
        user.setResult(result);
        ioService.outputString(Messages.RESULT.getMessage() + resultConverter.convert(user.getResult()));
        String resultMessage = checkService.check(result) ? Messages.WIN.getMessage() : Messages.LOOSE.getMessage();
        ioService.outputString(resultMessage);
    }
}
