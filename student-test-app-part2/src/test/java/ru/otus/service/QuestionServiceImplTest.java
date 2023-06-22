package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import ru.otus.dao.Parser;
import ru.otus.dao.ParserCsv;
import ru.otus.dao.QuestionDao;
import ru.otus.dao.QuestionDaoCsv;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.dto.QuestionDto;
import ru.otus.mapper.QuestionMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сервис вопросов")
class QuestionServiceImplTest {

    private List<Question> expected;
    private Parser<QuestionDto> parser;
    private QuestionDao dao;
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        QuestionMapper mapper = new QuestionMapper();
        Resource resource = new DefaultResourceLoader().getResource("/questionsTest.csv");
        parser = new ParserCsv<>(resource);
        dao = new QuestionDaoCsv(parser, mapper);
        questionService = new QuestionServiceImpl(dao);
        List<Answer> answers = List.of(
                new Answer("a1", false),
                new Answer("a2", true),
                new Answer("a3", false)
        );
        List<Answer> answers2 = List.of(
                new Answer("a1", false),
                new Answer("a2", true),
                new Answer("a3", false)
        );
        List<Answer> answers3 = List.of(
                new Answer("a1", false),
                new Answer("a2", true),
                new Answer("a3", false)
        );
        List<Answer> answers4 = List.of(
                new Answer("a1", false),
                new Answer("a2", false),
                new Answer("a3", true)
        );
        List<Answer> answers5 = List.of(
                new Answer("a1", false),
                new Answer("a2", true),
                new Answer("a3", false)
        );

        expected = List.of(
                new Question("question1", answers),
                new Question("question2", answers2),
                new Question("question3", answers3),
                new Question("question4", answers4),
                new Question("question5", answers5)
        );
    }

    @Test
    @DisplayName("должен возвращать список вопросов")
    void shouldGetAllQuestions() {
        List<Question> actual = questionService.getAllQuestion();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("должен возвращать список dto")
    void shouldGetAllQuestionDto() {
        List<QuestionDto> expected = List.of(
                new QuestionDto("question1", 2, List.of("a1", "a2", "a3")),
                new QuestionDto("question2", 2, List.of("a1", "a2", "a3")),
                new QuestionDto("question3", 2, List.of("a1", "a2", "a3")),
                new QuestionDto("question4", 3, List.of("a1", "a2", "a3")),
                new QuestionDto("question5", 2, List.of("a1", "a2", "a3"))
        );
        List<QuestionDto> actual = parser.parse(QuestionDto.class);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("должен возвращать список")
    void shouldGetAll() {
        List<Question> actual = dao.getAll();
        assertEquals(expected, actual);
    }
}