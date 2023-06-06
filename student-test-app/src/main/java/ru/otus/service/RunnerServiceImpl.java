package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.converter.QuestionConverter;
import ru.otus.utils.Printer;

@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

    private final QuestionService service;

    private final Printer printer;

    private final QuestionConverter converter;

    @Override
    public void printQuestions() {
        service.getAllQuestion().stream()
                .map(converter::convertToString)
                .forEach(printer::print);
    }
}
