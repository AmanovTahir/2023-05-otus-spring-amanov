package ru.otus.library.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

@DisplayName("Репозиторий для работы с коментариями должно")
@DataJpaTest
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager manager;

    @DisplayName("добавлять коментарий в БД")
    @Test
    public void shouldInsertIntoBD() {
        Comment comment = new Comment();
        comment.setBook(manager.find(Book.class, 1L));
        comment.setText("New Comment");

        Comment savedComment = commentRepository.save(comment);
        Assertions.assertNotNull(savedComment);
    }

    @DisplayName("обновить коментарий в БД")
    @Test
    public void shouldUpdateIntoBD() {
        Comment comment = manager.find(Comment.class, 1L);
        comment.setText("Updated Comment");

        Comment updatedComment = commentRepository.save(comment);
        Assertions.assertEquals("Updated Comment", updatedComment.getText());
    }

    @DisplayName("возвращать ожидаемый коментарий по id")
    @Test
    public void shouldGetById() {
        Optional<Comment> comment = commentRepository.findById(1L);
        Assertions.assertTrue(comment.isPresent());
        Assertions.assertEquals("comment_1", comment.get().getText());
    }

    @DisplayName("возвращать коментарии по id книги")
    @Test
    public void shouldFindAllByBookId() {
        List<Comment> comments = commentRepository.findAllByBookId(1L);
        Assertions.assertEquals(2, comments.size());
    }

    @DisplayName("удалять коментарий по id")
    @Test
    public void shouldDeleteById() {
        commentRepository.deleteById(1L);
        Comment comment = manager.find(Comment.class, 1L);
        Assertions.assertNull(comment);
    }
}