package ru.otus.dao;

import lombok.AllArgsConstructor;
import ru.otus.dto.QuestionDto;

import java.util.List;

@AllArgsConstructor
public class QuestionDaoCsv implements QuestionDao {
    private final Parser<QuestionDto> parser;

    @Override
    public List<QuestionDto> getAll() {
        return parser.parse(QuestionDto.class);
    }
}
