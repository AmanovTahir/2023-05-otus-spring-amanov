package ru.otus.library.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.library.dao.BookDao;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@DisplayName("Service для книг должен")
class BookServiceImplTest {

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("удалить по id")
    public void shouldDeleteById() {
        long id = 1L;

        bookService.deleteById(id);

        verify(bookDao).deleteById(id);
    }

    @Test
    @DisplayName("вернуть ожидаемый список")
    public void shouldGetAll() {
        Author author = new Author(1L, "Александр", "Пушкин");
        Category category = new Category(1L, "Категория");
        Book book = new Book("Книга", author, category);

        List<Book> expected = Collections.singletonList(book);
        when(bookDao.getAll()).thenReturn(expected);

        List<Book> actual = bookService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("вернуть книгу по id")
    public void shouldGetById() {
        long id = 1L;
        Author author = new Author(1L, "Александр", "Пушкин");
        Category category = new Category(1L, "Категория");
        Book expected = new Book("Книга", author, category);
        when(bookDao.getById(id)).thenReturn(Optional.of(expected));

        Book actual = bookService.getById(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("обновить книгу")
    public void shouldUpdate() {
        Author author = new Author(1L, "Александр", "Пушкин");
        Category category = new Category(1L, "Категория");
        Book expected = new Book("Книга", author, category);
        when(bookDao.update(expected)).thenReturn(Optional.of(expected));

        Book actual = bookService.update(expected);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("добавить книгу в БД")
    public void shouldInsertIntoBD() {
        Author author = new Author(1L, "Александр", "Пушкин");
        Category category = new Category(1L, "Категория");
        Book expected = new Book("Книга", author, category);
        when(bookDao.insert(expected)).thenReturn(Optional.of(expected));

        Book result = bookService.insert(expected);

        assertEquals(expected, result);
        verify(bookDao, times(1)).insert(expected);
    }

    @Test
    @DisplayName("вернуть ожидаемое кол-во")
    public void shouldGetCount() {
        int expected = 10;
        when(bookDao.count()).thenReturn(expected);

        int actual = bookService.count();

        assertEquals(expected, actual);
    }
}