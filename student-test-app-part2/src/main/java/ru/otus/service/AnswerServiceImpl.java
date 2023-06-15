package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Override
    public Answer get(Question question, int number) {
        return question.getAnswers().get(number - 1);
    }
}
