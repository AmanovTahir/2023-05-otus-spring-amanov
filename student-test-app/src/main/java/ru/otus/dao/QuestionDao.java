package ru.otus.dao;

import ru.otus.dto.QuestionDto;

import java.util.List;

public interface QuestionDao {
    List<QuestionDto> getAll();
}
