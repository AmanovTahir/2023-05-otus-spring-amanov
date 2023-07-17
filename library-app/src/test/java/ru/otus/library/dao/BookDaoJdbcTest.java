package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@DisplayName("Dao для работы с книгами должно")
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, CategoryDaoJdbc.class})
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDao;

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    public void shouldGetCount() {
        int count = bookDao.count();
        assertEquals(2, count);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    public void shouldInsertIntoBD() {
        Author author = new Author("firstName", "lastName");
        Category category = new Category("category");
        Book expected = new Book("new Book", author, category);

        Optional<Book> actualInsert = bookDao.insert(expected);
        Optional<Book> actualGet = bookDao.getById(3);

        assertEquals(expected, actualInsert.orElse(null));
        assertEquals(expected, actualGet.orElse(null));
    }

    @DisplayName("добавлять книгу в БД c сущетсвуещим автором")
    @Test
    public void shouldInsertIntoBDWithAuthor() {
        Author author = new Author(1, "Михаил", "Булгаков");
        Category category = new Category("category");
        Book expected = new Book("new Book", author, category);

        Optional<Book> actualInsert = bookDao.insert(expected);
        Optional<Book> actual = bookDao.getById(actualInsert.orElseThrow().getId());

        assertEquals(expected, actualInsert.orElse(null));
        assertEquals(expected, actual.orElse(null));
    }

    @DisplayName("добавлять книгу в БД c сущетсвуещим жанром")
    @Test
    public void shouldInsertIntoBDWithCategory() {
        Author author = new Author(1, "Михаил", "Булгаков");
        Category category = new Category(1, "классика");
        Book expected = new Book("new Book", author, category);

        Optional<Book> actualInsert = bookDao.insert(expected);
        Optional<Book> actual = bookDao.getById(actualInsert.orElseThrow().getId());

        assertEquals(expected, actualInsert.orElse(null));
        assertEquals(expected, actual.orElse(null));
    }


    @DisplayName("обнавление книги в БД")
    @Test
    @Transactional
    public void shouldUpdateIntoBD() {
        Author author = new Author(1, "Михаил", "Булгаков");
        Category category = new Category(1, "классика");
        Book beforeUpdate = new Book(1, "Мастер и Маргарита", author, category);

        Book bookUpdate = new Book(1, "bookUpdate", author, category);

        Optional<Book> actualUpdate = bookDao.update(bookUpdate);
        Optional<Book> afterUpdate = bookDao.getById(1);

        assertNotEquals(beforeUpdate, actualUpdate.orElse(null));
        assertNotEquals(beforeUpdate, afterUpdate.orElse(null));
    }


    @DisplayName("возвращать книгу по id")
    @Test
    public void shouldGetById() {
        Author author = new Author(1, "Михаил", "Булгаков");
        Category category = new Category(1, "классика");
        Book expected = new Book(1, "Мастер и Маргарита", author, category);

        Optional<Book> actual = bookDao.getById(1);

        assertEquals(expected, actual.orElse(null));
    }


    @DisplayName("возвращать ожидаемый список книг")
    @Test
    public void shouldGetAll() {
        List<Book> books = bookDao.getAll();
        assertEquals(2, books.size());
    }

    @DisplayName("удалять книгу по id")
    @Test
    public void shouldDeleteById() {
        bookDao.deleteById(1);
        assertThrows(EmptyResultDataAccessException.class, () ->
                bookDao.getById(1));
    }

}