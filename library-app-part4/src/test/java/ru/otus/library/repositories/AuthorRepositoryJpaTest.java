package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с авторами должно")
@DataMongoTest
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private BookRepository bookRepository;


    @DisplayName("добавлять автора в БД")
    @Test
    public void shouldInsertIntoBD() {
        Author expected = new Author("firstname3", "lastname3");
        Author actual = repository.save(expected);

        assertEquals(expected, actual);
    }

    @DisplayName("обновить автора в БД")
    @Test
    public void shouldUpdateIntoBD() {
        List<Author> all = repository.findAll();
        Author author = repository.findById(all.get(0).getId()).orElseThrow();
        author.setFirstName("NewAuthorName");

        Author expected = repository.save(author);

        Author actual = repository.findById(expected.getId()).orElseThrow();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    public void shouldGetById() {
        List<Author> all = repository.findAll();
        Author expected = all.get(0);
        Author actual = repository.findById(expected.getId()).orElseThrow();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }


    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    public void shouldGetAll() {
        int actual = repository.findAll().size();
        assertEquals(5, actual);
    }

    @DisplayName("удалять автора по id")
    @Test
    public void shouldDeleteById() {
        List<Author> all = repository.findAll();
        Author expected = repository.findById(all.get(0).getId()).orElseThrow();
        repository.deleteById(expected.getId());
        Book book = bookRepository.findByAuthorsContains(expected).orElseThrow();

        boolean contains = book.getAuthors().contains(expected);

        assertFalse(contains);
        assertThrows(NoSuchElementException.class,
                () -> repository.findById(expected.getId()).orElseThrow());
    }
}