package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.otus.domain.Result;
import ru.otus.domain.User;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final MessageService messageService;

    private final TestingService testingService;

    private final IOService ioService;

    private final ConversionService conversionService;

    private final CheckResultService checkService;

    @Override
    public void run(String... args) {
        String fullName = ioService.readStringWithPrompt(messageService.getMessage("hello"));
        User user = Objects.requireNonNull(conversionService.convert(fullName, User.class));
        Result result = testingService.testing();
        result.setUser(user);
        printResult(result);
    }

    private void printResult(Result result) {
        String resultAsString = conversionService.convert(result, String.class);
        ioService.outputString(messageService.getMessage("score", resultAsString));
        String resultMessage = checkService.check(result)
                ? messageService.getMessage("win")
                : messageService.getMessage("loose");
        ioService.outputString(resultMessage);
    }
}
