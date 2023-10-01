package ru.otus.library.services;

import ru.otus.library.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllByBookId(Long bookId);

    Comment update(Comment comment);

    Comment insert(Comment comment);

    void deleteByBookId(Long bookId);
}
