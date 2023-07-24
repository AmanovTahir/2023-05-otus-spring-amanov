package ru.otus.library.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.repositories.BookRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = BookServiceImpl.class)
@DisplayName("Service для книг должен")
class BookServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Test
    @DisplayName("удалить по id")
    public void shouldDeleteById() {
        long id = 1L;
        bookService.deleteById(id);
        verify(bookRepository).delete(id);
    }

    @Test
    @DisplayName("вернуть ожидаемый список")
    public void shouldGetAll() {
        List<Author> author = List.of(new Author(1L, "Александр", "Пушкин"));
        List<Category> category = List.of(new Category(1L, "Категория"));
        Book book = new Book("Книга", author, category);

        List<Book> expected = Collections.singletonList(book);
        when(bookRepository.findAll()).thenReturn(expected);

        List<Book> actual = bookService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("вернуть книгу по id")
    public void shouldGetById() {
        long id = 1L;
        List<Author> author = List.of(new Author(1L, "Александр", "Пушкин"));
        List<Category> category = List.of(new Category(1L, "Категория"));
        Book expected = new Book("Книга", author, category);
        when(bookRepository.findById(id)).thenReturn(Optional.of(expected));

        Book actual = bookService.getById(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("обновить книгу")
    public void shouldUpdate() {
        List<Author> author = List.of(new Author(1L, "Александр", "Пушкин"));
        List<Category> category = List.of(new Category(1L, "Категория"));
        Book expected = new Book("Книга", author, category);
        when(bookRepository.update(expected)).thenReturn(Optional.of(expected));

        Book actual = bookService.update(expected);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("добавить книгу в БД")
    public void shouldInsertIntoBD() {
        List<Author> author = List.of(new Author(1L, "Александр", "Пушкин"));
        List<Category> category = List.of(new Category(1L, "Категория"));
        Book expected = new Book("Книга", author, category);
        when(bookRepository.save(expected)).thenReturn(expected);

        Book result = bookService.insert(expected);

        assertEquals(expected, result);
        verify(bookRepository, times(1)).save(expected);
    }
}