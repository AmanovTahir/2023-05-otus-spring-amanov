package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.domain.Result;
import ru.otus.domain.User;
import ru.otus.service.AuthorizationProcessor;
import ru.otus.service.CheckResultService;
import ru.otus.service.MessageService;
import ru.otus.service.TestingService;

import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final TestingService testingService;

    private final ConversionService conversionService;

    private final MessageService messageService;

    private final AuthorizationProcessor authorisation;

    private final CheckResultService checkService;


    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        User user = Objects.requireNonNull(conversionService.convert(fullName, User.class));
        authorisation.setUser(user);
        return messageService.getMessage("hello", user.getName());
    }

    @ShellMethod(value = "Run test command", key = {"run", "test"})
    @ShellMethodAvailability(value = "isTestCommandAvailable")
    public String runTest() {
        Result result = testingService.testing();
        result.setUser(authorisation.getUser());
        return getStringResult(result);
    }

    private String getStringResult(Result result) {
        String resultAsString = conversionService.convert(result, String.class);
        return checkService.check(result)
                ? messageService.getMessage("win", resultAsString)
                : messageService.getMessage("loose", resultAsString);
    }

    private Availability isTestCommandAvailable() {
        return authorisation.isAuthorized()
                ? Availability.available()
                : Availability.unavailable(messageService.getMessage("error"));
    }
}
