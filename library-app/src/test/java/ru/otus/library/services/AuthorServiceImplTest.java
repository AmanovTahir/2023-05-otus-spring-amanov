package ru.otus.library.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.domain.Author;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Service для авторов должен")
class AuthorServiceImplTest {

    @Mock
    private AuthorDao authorDao;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    @DisplayName("удалить по id")
    public void shouldDeleteById() {
        long id = 1L;

        authorService.deleteById(id);

        verify(authorDao).deleteById(id);
    }

    @Test
    @DisplayName("вернуть ожидаемый список")
    public void shouldGetAll() {
        List<Author> expected = Collections.singletonList(new Author(1L, "Александр", "Пушкин"));
        when(authorDao.getAll()).thenReturn(expected);

        List<Author> actual = authorService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("вернуть автора по id")
    public void shouldGetById() {
        long id = 1L;
        Author expected = new Author(1L, "Александр", "Пушкин");
        when(authorDao.getById(id)).thenReturn(Optional.of(expected));
        Author actual = authorService.getById(id);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("обновить автора")
    public void shouldUpdate() {
        Author author = new Author(1L, "Александр", "Пушкин");
        when(authorDao.update(author)).thenReturn(Optional.of(author));

        Author actual = authorService.update(author);

        assertEquals(author, actual);
    }

    @Test
    @DisplayName("добавить автора в БД")
    public void shouldInsertIntoBD() {
        Author author = new Author("Александр", "Пушкин");
        when(authorDao.insert(author)).thenReturn(Optional.of(author));

        Author result = authorService.insert(author);

        assertEquals(author, result);
        verify(authorDao, times(1)).insert(author);
    }

    @Test
    @DisplayName("вернуть ожидаемое кол-во")
    public void shouldGetCount() {
        int expected = 10;
        when(authorDao.count()).thenReturn(expected);

        int actual = authorService.count();

        assertEquals(expected, actual);
    }
}