package ru.otus.library.services;

import ru.otus.library.domain.Comment;

import java.util.List;

public interface CommentService {
    void deleteById(String id);

    List<Comment> getAllByBookId(String bookId);

    Comment getById(String id);

    Comment update(Comment comment);

    Comment insert(Comment comment);

}
