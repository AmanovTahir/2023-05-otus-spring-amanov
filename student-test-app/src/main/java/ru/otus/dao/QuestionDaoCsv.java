package ru.otus.dao;

import lombok.AllArgsConstructor;
import ru.otus.domain.Question;

import java.util.List;

@AllArgsConstructor
public class QuestionDaoCsv implements QuestionDao {
    private final Parser<Question> parser;

    @Override
    public List<Question> getAll() {
        return parser.parse(Question.class);
    }
}
