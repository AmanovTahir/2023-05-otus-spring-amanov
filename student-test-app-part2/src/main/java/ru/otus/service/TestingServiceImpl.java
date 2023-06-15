package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.Converter;
import ru.otus.domain.Question;
import ru.otus.domain.Result;
import ru.otus.utils.AnswerListUtil;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    private final IOService ioService;

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final Converter<Question, String> questionConverter;

    private final AnswerListUtil answerListUtil;

    private static Result initResult(int size, long scores) {
        return Result.builder().total(size).pass(scores).build();
    }

    @Override
    public Result testing() {
        List<Question> allQuestion = questionService.getAllQuestion();
        long scores = getScores(allQuestion);
        return initResult(allQuestion.size(), scores);
    }

    private long getScores(List<Question> allQuestion) {
        return allQuestion.stream()
                .filter(this::isPass)
                .count();
    }

    private boolean isPass(Question question) {
        int number = ioService.readIntWithPrompt(questionConverter.convert(question));
        return answerListUtil.checkAnswerNumber(number, question.getAnswers().size()) &&
                answerService.get(question, number).isCorrect();
    }
}
