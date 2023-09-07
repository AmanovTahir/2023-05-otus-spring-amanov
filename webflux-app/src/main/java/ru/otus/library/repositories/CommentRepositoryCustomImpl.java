package ru.otus.library.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
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
}
