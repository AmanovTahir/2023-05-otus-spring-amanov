package ru.otus.library.repositories;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Book;
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

    @Override
    public void removeBookById(String id) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        Update update = new Update().pull("book", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
}
