package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.utils.ConsolePrinter;

@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {
    private final QuestionService service;

    private final ConsolePrinter printer;

    @Override
    public void printAnswers() {
        service.getAllQuestion().forEach(printer::printQuestion);
    }
}
