package ru.otus.library.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.dto.BookDto;
import ru.otus.library.mapper.AuthorMapper;
import ru.otus.library.mapper.BookMapper;
import ru.otus.library.mapper.CategoryMapper;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.services.impl.BookServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {
        BookServiceImpl.class, BookMapper.class, AuthorMapper.class, CategoryMapper.class
})
@DisplayName("Service для книг должен")
class BookServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private ConversionService conversionService;

    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    @Test
    @DisplayName("удалить по id")
    void shouldDeleteById() {
        String id = "123123";
        bookService.deleteById(id);
        verify(bookRepository).deleteById(id);
    }

    @Test
    @DisplayName("вернуть ожидаемый список")
    void shouldGetAll() {
        List<Author> author = List.of(new Author("Александр", "Пушкин"));
        List<Category> category = List.of(new Category("Категория"));
        Book book = new Book("Книга", author, category);
        BookDto bookDto = BookDto.builder().authors(List.of()).categories(List.of()).title("Книга").build();

        List<Book> expected = Collections.singletonList(book);
        when(bookRepository.findAll()).thenReturn(expected);
        when(conversionService.convert(book, BookDto.class)).thenReturn(bookDto);

        List<BookDto> actual = bookService.getAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        assertNotNull(actual);
    }

    @Test
    @DisplayName("вернуть книгу по id")
    void shouldGetById() {
        String id = "123123";
        List<Author> author = List.of(new Author("Александр", "Пушкин"));
        List<Category> category = List.of(new Category("Категория"));
        Book expected = new Book("Книга", author, category);
        BookDto bookDto = BookDto.builder().authors(List.of()).categories(List.of()).title("Книга").build();

        when(bookRepository.findById(id)).thenReturn(Optional.of(expected));
        when(conversionService.convert(bookDto, BookDto.class)).thenReturn(bookDto);

        Book actual = bookService.getById(id);

        assertNotNull(actual);
    }

    @Test
    @DisplayName("обновить книгу")
    void shouldUpdate() {
        List<Author> author = List.of(new Author("Александр", "Пушкин"));
        List<Category> category = List.of(new Category("Категория"));
        Book expected = new Book("Книга", author, category);
        BookDto bookDto = BookDto.builder().authors(List.of()).categories(List.of()).title("Книга").build();

        when(bookRepository.save(expected)).thenReturn(expected);
        when(conversionService.convert(bookDto, BookDto.class)).thenReturn(bookDto);

        BookDto actual = bookMapper.toDto(bookService.update(expected));

        assertNotNull(actual);
    }

    @Test
    @DisplayName("добавить книгу в БД")
    void shouldInsertIntoBD() {
        List<Author> author = List.of(new Author("Александр", "Пушкин"));
        List<Category> category = List.of(new Category("Категория"));
        Book expected = new Book("Книга", author, category);
        BookDto bookDto = BookDto.builder().authors(List.of()).categories(List.of()).title("Книга").build();

        when(bookRepository.save(expected)).thenReturn(expected);
        when(conversionService.convert(bookDto, BookDto.class)).thenReturn(bookDto);

        Book actual = bookService.update(expected);

        assertNotNull(actual);
        verify(bookRepository, times(1)).save(expected);
    }
}