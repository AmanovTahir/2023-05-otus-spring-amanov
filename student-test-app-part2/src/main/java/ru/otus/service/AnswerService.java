package ru.otus.service;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;

public interface AnswerService {
    Answer get(Question question, int number);
}
