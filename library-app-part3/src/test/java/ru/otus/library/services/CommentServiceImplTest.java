package ru.otus.library.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = CommentServiceImpl.class)
@DisplayName("Service для комментариев должен")
public class CommentServiceImplTest {

    @MockBean
    private CommentRepository repository;

    @Autowired
    private CommentService service;

    @Test
    @DisplayName("удалить по id")
    public void shouldDeleteById() {
        long id = 1L;

        service.deleteById(id);

        verify(repository).deleteById(id);
    }

    @Test
    @DisplayName("вернуть жанр по id")
    public void shouldGetById() {
        long id = 1L;
        Book book = new Book("book", List.of(), List.of());
        Comment expected = new Comment(id, "Comment", book);
        when(repository.findById(id)).thenReturn(Optional.of(expected));

        Comment actual = service.getById(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("обновить жанр")
    public void shouldUpdate() {
        long id = 1L;
        Book book = new Book("book", List.of(), List.of());
        Comment expected = new Comment(id, "Comment", book);
        when(repository.save(expected)).thenReturn(expected);

        Comment actual = service.update(expected);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("добавить жанр в БД")
    public void shouldInsertIntoBD() {
        long id = 1L;
        Book book = new Book("book", List.of(), List.of());
        Comment expected = new Comment(id, "Comment", book);
        when(repository.save(expected)).thenReturn(expected);

        Comment actual = service.update(expected);

        assertEquals(expected, actual);
        verify(repository, times(1)).save(expected);
    }
}
