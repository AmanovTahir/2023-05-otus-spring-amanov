package ru.otus.library.repositories;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Book;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public void removeAuthorsArrayElementsById(String id) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        Update update = new Update().pull("author", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }

    @Override
    public void removeCategoriesArrayElementsById(String id) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        Update update = new Update().pull("category", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
}
