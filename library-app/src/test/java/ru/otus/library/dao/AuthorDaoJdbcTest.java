package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@JdbcTest
@DisplayName("Dao для работы с авторами должно")
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc daoJdbc;

    @DisplayName("возвращать ожидаемое количество авторов в БД")
    @Test
    public void shouldGetCount() {
        int count = daoJdbc.count();
        assertEquals(2, count);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    public void shouldInsertIntoBD() {
        Author expected = new Author("firstname3", "lastname3");

        Optional<Author> actualInsert = daoJdbc.insert(expected);
        Optional<Author> actualGet = daoJdbc.getById(3);

        assertEquals(expected, actualInsert.orElse(null));
        assertEquals(expected, actualGet.orElse(null));
    }

    @DisplayName("обновить автора в БД")
    @Test
    public void shouldUpdateIntoBD() {
        Author beforeUpdate = new Author("Михаил", "Булгаков");
        Author authorUpdate = new Author(1, "firstNameUpdate", "lastNameUpdate");

        Optional<Author> actualAfterUpdate = daoJdbc.update(authorUpdate);
        Optional<Author> actualGet = daoJdbc.getById(1);

        assertNotEquals(beforeUpdate, actualAfterUpdate.orElse(null));
        assertNotEquals(beforeUpdate, actualGet.orElse(null));
    }

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    public void shouldGetById() {
        Author expected = new Author("Михаил", "Булгаков");

        Optional<Author> actualInsert = daoJdbc.insert(expected);
        Author actual = daoJdbc.getById(4).orElseThrow();

        assertEquals(expected, actualInsert.orElse(null));
        assertEquals(expected.getFirstName(), actual.getFirstName());
    }


    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    public void shouldGetAll() {
        List<Author> authors = daoJdbc.getAll();
        assertEquals(2, authors.size());
    }

    @DisplayName("удалять автора по id")
    @Test
    public void shouldDeleteById() {
        daoJdbc.deleteById(1);
        assertEquals(Optional.empty(), daoJdbc.getById(1));
    }
}