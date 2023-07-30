package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Репозиторий для работы с книгами должно")
@DataJpaTest
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepositoryJpa;

    @Autowired
    private CategoryRepository categoryRepositoryJpa;

    @Autowired
    private AuthorRepository authorRepositoryJpa;

    @Autowired
    private TestEntityManager manager;


    @DisplayName("добавлять книгу в БД")
    @Test
    public void shouldInsertIntoBD() {
        Author author = authorRepositoryJpa.save(new Author("firstName", "lastName"));
        Category category = categoryRepositoryJpa.save(new Category("category"));

        Book expected = new Book("new Book", List.of(author), List.of(category));

        Book actualInsert = bookRepositoryJpa.save(expected);
        Book managerGet = manager.find(Book.class, actualInsert.getId());

        assertEquals(expected, actualInsert);
        assertEquals(expected, managerGet);
    }

    @DisplayName("добавлять книгу в БД c сущетсвуещим автором")
    @Test
    public void shouldInsertIntoBDWithAuthor() {
        Author author = manager.find(Author.class, 2);
        Category category = categoryRepositoryJpa.save(new Category("category"));

        Book expected = new Book("new Book", List.of(author), List.of(category));
        Book actualInsert = bookRepositoryJpa.save(expected);
        Book managerGet = manager.find(Book.class, actualInsert.getId());

        assertEquals(expected, actualInsert);
        assertEquals(expected, managerGet);
    }

    @DisplayName("добавлять книгу в БД c сущетсвуещим жанром")
    @Test
    public void shouldInsertIntoBDWithCategory() {
        Author author = authorRepositoryJpa.save(new Author("firstName", "lastName"));
        Category category = manager.find(Category.class, 1);
        Book expected = new Book("new Book", List.of(author), List.of(category));

        Book actualInsert = bookRepositoryJpa.save(expected);
        Book managerGet = manager.find(Book.class, actualInsert.getId());

        assertEquals(expected, actualInsert);
        assertEquals(expected, managerGet);
    }


    @DisplayName("обнавление книги в БД")
    @Test
    public void shouldUpdateIntoBD() {
        Author author = manager.find(Author.class, 1);
        Category category = manager.find(Category.class, 1);

        Book beforeUpdate = new Book(1, "Мастер и Маргарита", List.of(author), List.of(category));
        Book bookUpdate = new Book(1, "bookUpdate", List.of(author), List.of(category));

        Book actualUpdate = bookRepositoryJpa.save(bookUpdate);
        Optional<Book> afterUpdate = bookRepositoryJpa.findById(1L);
        Book managerGet = manager.find(Book.class, 1);

        assertNotEquals(beforeUpdate, actualUpdate);
        assertNotEquals(beforeUpdate, afterUpdate.orElse(null));
        assertNotEquals(beforeUpdate, managerGet);
    }


    @DisplayName("возвращать книгу по id")
    @Test
    public void shouldGetById() {
        Book actual = bookRepositoryJpa.findById(1L).orElseThrow();
        Book expected = manager.find(Book.class, 1);
        assertEquals(expected, actual);
    }


    @DisplayName("возвращать ожидаемый список книг")
    @Test
    public void shouldGetAll() {
        List<Author> authors = authorRepositoryJpa.findAll();
        assertEquals(2, authors.size());
    }

    @DisplayName("удалять книгу по id")
    @Test
    public void shouldDeleteById() {
        authorRepositoryJpa.deleteById(1L);
        assertEquals(Optional.empty(), authorRepositoryJpa.findById(1L));
    }
}