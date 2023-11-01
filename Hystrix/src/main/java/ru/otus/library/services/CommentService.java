package ru.otus.library.services;

import ru.otus.library.domain.Comment;
import ru.otus.library.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void deleteById(String id);

    List<CommentDto> getAllByBookId(String bookId);

    Comment getById(String id);

    Comment update(Comment comment);

    Comment insert(Comment comment);

}
