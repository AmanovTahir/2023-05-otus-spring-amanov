package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.dto.QuestionDto;
import ru.otus.utils.Printer;

@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

    private final QuestionService service;

    private final Printer printer;

    @Override
    public void printQuestions() {
        for (QuestionDto questionDto : service.getAllQuestion()) {
            printer.print(questionDto.getQuestion(), questionDto.getAnswers());
        }
    }
}
