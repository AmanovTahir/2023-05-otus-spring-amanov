package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с коментариями должно")
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private BookRepository bookRepository;


    @DisplayName("добавлять коментарий в БД")
    @Test
    public void shouldInsertIntoBD() {
        List<Book> all = bookRepository.findAll();
        Book book = all.get(0);
        Comment expected = new Comment("new comment", book);
        Comment actual = repository.save(expected);

        assertEquals(expected, actual);
    }

    @DisplayName("обновить коментарий в БД")
    @Test
    public void shouldUpdateIntoBD() {
        List<Comment> all = repository.findAll();
        Comment expected = all.get(0);
        expected.setText("update comment");

        Comment actual = repository.save(expected);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getBook(), actual.getBook());
    }

    @DisplayName("возвращать ожидаемый коментарий по id")
    @Test
    public void shouldGetById() {
        List<Comment> all = repository.findAll();
        Comment expected = all.get(0);
        Comment actual = repository.findById(expected.getId()).orElseThrow();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getBook().getTitle(), actual.getBook().getTitle());
    }

    @DisplayName("возвращать коментарии по id книги")
    @Test
    public void shouldFindAllByBookId() {
        List<String> actual = List.of(
                "Увлекательная научная фантастика, читал на одном дыхании!",
                "Персонажи такие живые, что кажется, будто сам в космосе путешествуешь!");

        List<Book> all = bookRepository.findAll();
        Book book = all.get(0);

        List<Comment> comments = repository.findAllByBookId(book.getId());
        List<String> expected = comments.stream().map(Comment::getText).toList();

        assertEquals(actual, expected);
    }

    @DisplayName("удалять коментарий по id")
    @Test
    public void shouldDeleteById() {
        List<Comment> all = repository.findAll();
        Comment expected = repository.findById(all.get(0).getId()).orElseThrow();
        repository.deleteById(expected.getId());

        assertThrows(NoSuchElementException.class,
                () -> repository.findById(expected.getId()).orElseThrow());
    }

    @DisplayName("удалять книгу из коментария")
    @Test
    public void shouldDeleteBook() {
        List<Book> books = bookRepository.findAll();
        Book book = books.get(0);
        bookRepository.deleteById(book.getId());

        List<Comment> comments = repository.findAll();
        Comment expected = repository.findById(comments.get(0).getId()).orElseThrow();
        assertNull(expected.getBook());
    }
}