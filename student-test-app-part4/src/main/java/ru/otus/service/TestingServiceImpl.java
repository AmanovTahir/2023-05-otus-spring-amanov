package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.domain.Result;
import ru.otus.exception.AnswerIndexOutOfBoundsException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    private final IOService ioService;

    private final QuestionService questionService;

    private final ConversionService conversionService;

    @Override
    public Result testing() {
        List<Question> allQuestion = questionService.getAllQuestion();
        long scores = getScores(allQuestion);
        return initResult(allQuestion.size(), scores);
    }

    private Result initResult(int size, long scores) {
        return Result.builder().total(size).pass(scores).build();
    }

    private long getScores(List<Question> allQuestion) {
        return allQuestion.stream()
                .filter(this::isPass)
                .count();
    }

    private boolean isPass(Question question) {
        String questionString = conversionService.convert(question, String.class);
        int number = ioService.readIntWithPrompt(questionString);
        return checkAnswerNumber(number, question.getAnswers().size()) && getAnswer(question, number).isCorrect();
    }

    private Answer getAnswer(Question question, int number) {
        return question.getAnswers().get(number - 1);
    }

    private boolean checkAnswerNumber(int answerNumber, int AnswersCount) {
        if (answerNumber <= 0 || answerNumber > AnswersCount) {
            throw new AnswerIndexOutOfBoundsException("Given number of answer is out of range");
        }
        return true;
    }
}
