package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("DAO вопросов")
@ComponentScan({"ru.otus.dao", "ru.otus.mapper"})
@SpringBootTest
class QuestionDaoCsvTest {

    @Autowired
    private QuestionDao questionDao;

    private List<Question> expected;

    @BeforeEach
    void setUp() {
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
    void shouldGetQuestions() {
        List<Question> result = questionDao.getAll();
        assertArrayEquals(expected.toArray(), result.toArray());
    }
}