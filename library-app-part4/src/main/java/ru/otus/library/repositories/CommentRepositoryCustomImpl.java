package ru.otus.library.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Comment;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Comment> findAllByBookId(String bookId) {
        Query query = new Query(Criteria.where("book.id").is(bookId));
        return mongoTemplate.find(query, Comment.class);
    }
}
