package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Репозиторий для работы с авторами должно")
@DataJpaTest
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private TestEntityManager manager;

    @DisplayName("добавлять автора в БД")
    @Test
    public void shouldInsertIntoBD() {
        Author expected = new Author("firstname3", "lastname3");
        System.out.println(repository.findAll());
        Author actualInsert = repository.save(expected);
        Author actualGet = manager.find(Author.class, actualInsert.getId());

        assertEquals(expected, actualInsert);
        assertEquals(expected, actualGet);
    }

    @DisplayName("обновить автора в БД")
    @Test
    public void shouldUpdateIntoBD() {
        Author beforeUpdate = new Author("Михаил", "Булгаков");
        Author authorUpdate = new Author(1, "firstNameUpdate", "lastNameUpdate");

        Author actualAfterUpdate = repository.save(authorUpdate);
        Author actualGet = manager.find(Author.class, 1);

        assertNotEquals(beforeUpdate, actualAfterUpdate);
        assertNotEquals(beforeUpdate, actualGet);
    }

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    public void shouldGetById() {
        Author expected = new Author("Михаил", "Булгаков");

        Author actualInsert = repository.save(expected);
        Author actualGet = manager.find(Author.class, actualInsert.getId());

        assertEquals(expected, actualInsert);
        assertEquals(expected.getFirstName(), actualGet.getFirstName());
    }


    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    public void shouldGetAll() {
        List<Author> authors = repository.findAll();
        assertEquals(2, authors.size());
    }

    @DisplayName("удалять автора по id")
    @Test
    public void shouldDeleteById() {
        repository.deleteById(1L);
        assertEquals(Optional.empty(), repository.findById(1L));
    }
}