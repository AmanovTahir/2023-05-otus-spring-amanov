package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;

    @Override
    public List<Question> getAllQuestion() {
        return dao.getAll();
    }
}
