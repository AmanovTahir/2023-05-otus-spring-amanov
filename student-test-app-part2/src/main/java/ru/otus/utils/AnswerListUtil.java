package ru.otus.utils;

import org.springframework.stereotype.Component;
import ru.otus.exception.AnswerIndexOutOfBoundsException;

@Component
public class AnswerListUtil {
    public boolean checkAnswerNumber(int answerNumber, int AnswersCount) {
        if (answerNumber <= 0 || answerNumber > AnswersCount) {
            throw new AnswerIndexOutOfBoundsException("Given number of answer is out of range");
        }
        return true;
    }
}
