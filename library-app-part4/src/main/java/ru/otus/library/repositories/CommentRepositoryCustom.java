package ru.otus.library.repositories;

import ru.otus.library.domain.Comment;

import java.util.List;

public interface CommentRepositoryCustom {

    List<Comment> findAllByBookId(String id);

}
