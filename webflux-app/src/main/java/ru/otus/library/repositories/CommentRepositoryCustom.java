package ru.otus.library.repositories;

import reactor.core.publisher.Flux;
import ru.otus.library.domain.Comment;

public interface CommentRepositoryCustom {

    Flux<Comment> findAllByBookId(String id);

}
