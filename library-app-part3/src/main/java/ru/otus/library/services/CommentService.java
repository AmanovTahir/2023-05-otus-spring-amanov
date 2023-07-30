package ru.otus.library.services;

import ru.otus.library.domain.Comment;

import java.util.List;

public interface CommentService {
    void deleteById(long id);

    List<Comment> getAllByBookId(long bookId);

    Comment getById(long id);

    Comment update(Comment comment);

    Comment insert(Comment comment);

}
