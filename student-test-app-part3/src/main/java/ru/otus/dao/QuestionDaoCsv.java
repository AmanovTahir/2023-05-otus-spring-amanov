package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Question;
import ru.otus.dto.QuestionDto;
import ru.otus.mapper.QuestionMapper;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class QuestionDaoCsv implements QuestionDao {

    private final Parser<QuestionDto> parserScv;

    private final QuestionMapper mapper;

    @Override
    public List<Question> getAll() {
        List<QuestionDto> dtoList = parserScv.parse(QuestionDto.class);
        return mapper.mapToQuestionList(dtoList);
    }
}
