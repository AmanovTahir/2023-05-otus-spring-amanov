package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с жанрами должно")
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CategoryRepositoryJpaTest {


    @Autowired
    private CategoryRepository repository;

    @Autowired
    private BookRepository bookRepository;


    @DisplayName("добавлять жанр в БД")
    @Test
    public void shouldInsertIntoBD() {
        Category expected = new Category("new category");
        Category actual = repository.save(expected);

        assertEquals(expected, actual);
    }

    @DisplayName("обновить жанр в БД")
    @Test
    public void shouldUpdateIntoBD() {
        List<Category> all = repository.findAll();
        Category category = repository.findById(all.get(0).getId()).orElseThrow();
        category.setName("update category");

        Category expected = repository.save(category);

        Category actual = repository.findById(expected.getId()).orElseThrow();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    public void shouldGetById() {
        List<Category> all = repository.findAll();
        Category expected = all.get(0);
        Category actual = repository.findById(expected.getId()).orElseThrow();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }


    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    public void shouldGetAll() {
        int actual = repository.findAll().size();
        assertEquals(5, actual);
    }

    @DisplayName("удалять жанр по id")
    @Test
    public void shouldDeleteById() {
        List<Category> all = repository.findAll();
        Category expected = repository.findById(all.get(0).getId()).orElseThrow();
        repository.deleteById(expected.getId());
        Book book = bookRepository.findByCategoriesContains(expected).orElseThrow();

        boolean contains = book.getCategories().contains(expected);

        assertFalse(contains);
        assertThrows(NoSuchElementException.class,
                () -> repository.findById(expected.getId()).orElseThrow());
    }
}