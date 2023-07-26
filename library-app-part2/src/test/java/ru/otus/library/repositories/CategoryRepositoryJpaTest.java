package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Category;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Репозиторий для работы с жанрами должно")
@DataJpaTest
@Import(CategoryRepositoryJpa.class)
class CategoryRepositoryJpaTest {


    @Autowired
    private CategoryRepositoryJpa jpa;

    @Autowired
    private TestEntityManager manager;


    @DisplayName("добавлять жанр в БД")
    @Test
    public void shouldInsertIntoBD() {
        Category category = new Category("Category 103");
        Category insert = jpa.save(category);

        Category actualGet = manager.find(Category.class, insert.getId());

        assertEquals(category, insert);
        assertEquals(category, actualGet);
    }

    @DisplayName("обновить жанр в БД")
    @Test
    public void shouldUpdateIntoBD() {
        Category beforeUpdate = new Category(1, "классика");
        Optional<Category> update = jpa.update(new Category(1, "category_update"));

        Category afterUpdate = manager.find(Category.class, 1);

        assertNotEquals(beforeUpdate, update.orElse(null));
        assertNotEquals(beforeUpdate, afterUpdate);
    }

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    public void shouldGetById() {
        Category expected = manager.find(Category.class, 1);
        Category actual = jpa.findById(1).orElseThrow();

        assertEquals(expected, actual);
    }


    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    public void shouldGetAll() {
        List<Category> categories = jpa.findAll();
        assertEquals(2, categories.size());
    }

    @DisplayName("удалять жанр по id")
    @Test
    public void shouldDeleteById() {
        jpa.delete(1);
        assertEquals(Optional.empty(), jpa.findById(1));
    }

}