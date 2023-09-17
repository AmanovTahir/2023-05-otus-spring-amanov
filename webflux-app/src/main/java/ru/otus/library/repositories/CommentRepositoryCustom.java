package ru.otus.library.repositories;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

public interface CommentRepositoryCustom {

    Flux<Comment> findAllByBookId(String id);

    Mono<Void> deleteAllBookById(String id);

    Mono<Void> addBookToComments(Book book);
}
