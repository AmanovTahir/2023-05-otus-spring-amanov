package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Репозиторий для работы с книгами должно")
@DataMongoTest
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepositoryJpa;

    @Autowired
    private CategoryRepository categoryRepositoryJpa;

    @Autowired
    private AuthorRepository authorRepositoryJpa;


    @DisplayName("добавлять книгу в БД")
    @Test
    public void shouldInsertIntoBD() {
        Author author = authorRepositoryJpa.save(new Author("firstName", "lastName"));
        Category category = categoryRepositoryJpa.save(new Category("category"));
        Book expected = new Book("new Book", List.of(author), List.of(category));
        Book actualInsert = bookRepositoryJpa.save(expected);
        assertEquals(expected, actualInsert);

        bookRepositoryJpa.delete(expected);
    }

    @DisplayName("добавлять книгу в БД c сущетсвуещим автором")
    @Test
    public void shouldInsertIntoBDWithAuthor() {
        List<Author> all = authorRepositoryJpa.findAll();
        Author author = authorRepositoryJpa.findById(all.get(0).getId()).orElseThrow();
        Category category = categoryRepositoryJpa.save(new Category("category"));

        Book expected = new Book("new Book", List.of(author), List.of(category));
        Book actualInsert = bookRepositoryJpa.save(expected);

        assertEquals(expected, actualInsert);
    }

    @DisplayName("добавлять книгу в БД c сущетсвуещим жанром")
    @Test
    public void shouldInsertIntoBDWithCategory() {
        Author author = authorRepositoryJpa.save(new Author("firstName", "lastName"));

        List<Category> all = categoryRepositoryJpa.findAll();
        Category category = categoryRepositoryJpa.findById(all.get(0).getId()).orElseThrow();

        Book expected = new Book("new Book", List.of(author), List.of(category));

        Book actualInsert = bookRepositoryJpa.save(expected);

        assertEquals(expected, actualInsert);
    }


    @DisplayName("обнавление книги в БД")
    @Test
    public void shouldUpdateIntoBD() {
        List<Book> all = bookRepositoryJpa.findAll();
        Book expected = all.get(0);
        expected.setTitle("Новое название книги");

        Book actual = bookRepositoryJpa.save(expected);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getAuthors(), actual.getAuthors());
        assertEquals(expected.getCategories(), actual.getCategories());
    }


    @DisplayName("возвращать книгу по id")
    @Test
    public void shouldGetById() {
        List<Book> all = bookRepositoryJpa.findAll();
        Book expected = all.get(0);

        Book actual = bookRepositoryJpa.findById(expected.getId()).orElseThrow();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
    }


    @DisplayName("возвращать ожидаемый список книг")
    @Test
    public void shouldGetAll() {
        List<Book> books = bookRepositoryJpa.findAll();
        assertEquals(5, books.size());

    }

    @DisplayName("удалять книгу по id")
    @Test
    public void shouldDeleteById() {
        List<Book> all = bookRepositoryJpa.findAll();
        Book expected = all.get(0);

        bookRepositoryJpa.deleteById(expected.getId());

        assertThrows(NoSuchElementException.class,
                () -> bookRepositoryJpa.findById(expected.getId()).orElseThrow());
    }
}