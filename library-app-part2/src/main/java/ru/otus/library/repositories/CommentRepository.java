package ru.otus.library.repositories;

import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> update(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAllByBookId(long bookId);

    void delete(long id);

}
