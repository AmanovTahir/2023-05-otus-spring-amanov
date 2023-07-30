package ru.otus.library.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.domain.Author;
import ru.otus.library.repositories.AuthorRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = AuthorServiceImpl.class)
@DisplayName("Service для авторов должен")
class AuthorServiceImplTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Test
    @DisplayName("удалить по id")
    public void shouldDeleteById() {
        long id = 1L;

        authorService.deleteById(id);

        verify(authorRepository).deleteById(id);
    }

    @Test
    @DisplayName("вернуть ожидаемый список")
    public void shouldGetAll() {
        List<Author> expected = Collections.singletonList(new Author(1L, "Александр", "Пушкин"));
        when(authorRepository.findAll()).thenReturn(expected);

        List<Author> actual = authorService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("вернуть автора по id")
    public void shouldGetById() {
        long id = 1L;
        Author expected = new Author(1L, "Александр", "Пушкин");
        when(authorRepository.findById(id)).thenReturn(Optional.of(expected));
        Author actual = authorService.getById(id);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("обновить автора")
    public void shouldUpdate() {
        Author author = new Author(1L, "Александр", "Пушкин");
        when(authorRepository.save(author)).thenReturn(author);

        Author actual = authorService.update(author);

        assertEquals(author, actual);
    }

    @Test
    @DisplayName("добавить автора в БД")
    public void shouldInsertIntoBD() {
        Author author = new Author("Александр", "Пушкин");
        when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.insert(author);

        assertEquals(author, result);
        verify(authorRepository, times(1)).save(author);
    }
}