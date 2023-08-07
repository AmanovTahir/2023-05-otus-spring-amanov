package ru.otus.library.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Author;
import ru.otus.library.events.MongoAuthorCascadeDeleteEventsListener;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Репозиторий для работы с авторами должно")
@DataMongoTest
@Import(MongoAuthorCascadeDeleteEventsListener.class)
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;


    @DisplayName("добавлять автора в БД")
    @Test
    public void shouldInsertIntoBD() {
        Author expected = new Author("firstname3", "lastname3");
        Author actual = authorRepository.save(expected);
        assertEquals(expected, actual);

        authorRepository.delete(expected);
    }

    @DisplayName("обновить автора в БД")
    @Test
    public void shouldUpdateIntoBD() {
        List<Author> all = authorRepository.findAll();
        Author author = authorRepository.findById(all.get(0).getId()).orElseThrow();
        author.setFirstName("NewAuthorName");

        Author expected = authorRepository.save(author);

        Author actual = authorRepository.findById(expected.getId()).orElseThrow();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    public void shouldGetById() {
        List<Author> all = authorRepository.findAll();
        Author expected = all.get(0);
        Author actual = authorRepository.findById(expected.getId()).orElseThrow();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }


    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    public void shouldGetAll() {
        int actual = authorRepository.findAll().size();
        assertEquals(5, actual);
    }

    @DisplayName("удалять автора по id")
    @Test
    public void shouldDeleteById() {
        val all = authorRepository.findAll();
        val expected = authorRepository.findById(all.get(0).getId()).orElseThrow();
        val authorId = expected.getId();
        authorRepository.deleteById(expected.getId());

        assertThrows(NoSuchElementException.class,
                () -> authorRepository.findById(authorId).orElseThrow());
        assertThrows(NoSuchElementException.class,
                () -> bookRepository.findByAuthorsId(authorId).orElseThrow());
    }
}