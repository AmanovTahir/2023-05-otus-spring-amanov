package ru.otus.dao;

import lombok.AllArgsConstructor;
import ru.otus.domain.Question;
import ru.otus.dto.QuestionRecord;
import ru.otus.mapper.QuestionMapper;

import java.util.List;

@AllArgsConstructor
public class QuestionDaoCsv implements QuestionDao {

    private final Parser<QuestionRecord> parser;

    private final QuestionMapper mapper;

    @Override
    public List<Question> getAll() {
        List<QuestionRecord> dtoList = parser.parse(QuestionRecord.class);
        return mapper.mapToQuestionList(dtoList);
    }
}
