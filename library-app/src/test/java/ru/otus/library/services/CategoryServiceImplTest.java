package ru.otus.library.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.library.dao.CategoryDao;
import ru.otus.library.domain.Category;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CategoryServiceImpl.class})
@DisplayName("Service для жанров должен")
class CategoryServiceImplTest {

    @MockBean
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("удалить по id")
    public void shouldDeleteById() {
        long id = 1L;

        categoryService.deleteById(id);

        verify(categoryDao).deleteById(id);
    }

    @Test
    @DisplayName("вернуть ожидаемый список")
    public void shouldGetAll() {
        Category category = new Category(1L, "Категория");

        List<Category> expected = Collections.singletonList(category);
        when(categoryDao.getAll()).thenReturn(expected);

        List<Category> actual = categoryService.getAll();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("вернуть жанр по id")
    public void shouldGetById() {
        long id = 1L;
        Category expected = new Category(1L, "Категория");
        when(categoryDao.getById(id)).thenReturn(Optional.of(expected));

        Category actual = categoryService.getById(id);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("обновить жанр")
    public void shouldUpdate() {
        Category expected = new Category(1L, "Категория");
        when(categoryDao.update(expected)).thenReturn(Optional.of(expected));

        Category actual = categoryService.update(expected);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("добавить жанр в БД")
    public void shouldInsertIntoBD() {
        Category expected = new Category(1L, "Категория");
        when(categoryDao.insert(expected)).thenReturn(Optional.of(expected));

        Category result = categoryService.insert(expected);

        assertEquals(expected, result);
        verify(categoryDao, times(1)).insert(expected);
    }

    @Test
    @DisplayName("вернуть ожидаемое кол-во")
    public void shouldGetCount() {
        int expected = 10;
        when(categoryDao.count()).thenReturn(expected);

        int actual = categoryService.count();

        assertEquals(expected, actual);
    }

}