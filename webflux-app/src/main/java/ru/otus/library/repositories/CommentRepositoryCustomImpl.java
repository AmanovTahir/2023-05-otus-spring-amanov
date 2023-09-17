package ru.otus.library.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;


@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Comment> findAllByBookId(String bookId) {
        Query query = new Query(Criteria.where("book.id").is(bookId));
        return mongoTemplate.find(query, Comment.class);
    }

    @Override
    public Mono<Void> deleteAllBookById(String bookId) {
        Query query = new Query(Criteria.where("book.id").is(bookId));
        return mongoTemplate.remove(query, Comment.class).then();
    }

    @Override
    public Mono<Void> addBookToComments(Book book) {
        Update update = new Update().set("book", book);
        Query query = new Query();
        return mongoTemplate.updateMulti(query, update, Comment.class).then();
    }
}
