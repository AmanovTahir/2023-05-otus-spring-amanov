package ru.otus.library.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Category;
import ru.otus.library.events.MongoCategoryCascadeDeleteEventsListener;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Репозиторий для работы с жанрами должно")
@DataMongoTest
@Import(MongoCategoryCascadeDeleteEventsListener.class)
class CategoryRepositoryJpaTest {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;


    @DisplayName("добавлять жанр в БД")
    @Test
    public void shouldInsertIntoBD() {
        Category expected = new Category("new category");
        Category actual = categoryRepository.save(expected);
        assertEquals(expected, actual);

        categoryRepository.delete(expected);
    }

    @DisplayName("обновить жанр в БД")
    @Test
    public void shouldUpdateIntoBD() {
        List<Category> all = categoryRepository.findAll();
        Category category = categoryRepository.findById(all.get(0).getId()).orElseThrow();
        category.setName("update category");

        Category expected = categoryRepository.save(category);

        Category actual = categoryRepository.findById(expected.getId()).orElseThrow();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    public void shouldGetById() {
        List<Category> all = categoryRepository.findAll();
        Category expected = all.get(0);
        Category actual = categoryRepository.findById(expected.getId()).orElseThrow();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }


    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    public void shouldGetAll() {
        int actual = categoryRepository.findAll().size();
        assertEquals(5, actual);
    }

    @DisplayName("удалять жанр по id")
    @Test
    public void shouldDeleteById() {
        val all = categoryRepository.findAll();
        val expected = categoryRepository.findById(all.get(0).getId()).orElseThrow();
        val categoryId = expected.getId();
        categoryRepository.deleteById(expected.getId());

        assertThrows(NoSuchElementException.class,
                () -> categoryRepository.findById(categoryId).orElseThrow());
        assertThrows(NoSuchElementException.class,
                () -> bookRepository.findByCategoriesId(categoryId).orElseThrow());
    }
}