package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Category;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@JdbcTest
@DisplayName("Dao для работы с жанрами должно")
@Import(CategoryDaoJdbc.class)
public class CategoryDaoJdbcTest {

    @Autowired
    private CategoryDaoJdbc categoryDao;

    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    public void shouldGetCount() {
        int count = categoryDao.count();
        assertEquals(2, count);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    public void shouldInsertIntoBD() {
        Category category = new Category("Category 103");
        Optional<Category> insert = categoryDao.insert(category);

        assertEquals(category, insert.orElse(null));
        assertEquals(category, categoryDao.getById(3).orElse(null));
    }

    @DisplayName("обновить жанр в БД")
    @Test
    public void shouldUpdateIntoBD() {
        Category beforeUpdate = new Category(1, "классика");

        Category categoryUpdate = new Category(1, "category_update");
        Optional<Category> update = categoryDao.update(categoryUpdate);

        Optional<Category> afterUpdate = categoryDao.getById(1);
        assertNotEquals(beforeUpdate, update.orElse(null));
        assertNotEquals(beforeUpdate, afterUpdate.orElse(null));
    }

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    public void shouldGetById() {
        Category expected = new Category("Category 103");

        Optional<Category> insert = categoryDao.insert(expected);
        Category actual = categoryDao.getById(4).orElseThrow();

        assertEquals(expected, insert.orElse(null));
        assertEquals("Category 103", actual.getName());
    }


    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    public void shouldGetAll() {
        List<Category> categories = categoryDao.getAll();
        assertEquals(2, categories.size());
    }

    @DisplayName("удалять жанр по id")
    @Test
    public void shouldDeleteById() {
        categoryDao.deleteById(1);
        assertEquals(Optional.empty(), categoryDao.getById(1));
    }
}