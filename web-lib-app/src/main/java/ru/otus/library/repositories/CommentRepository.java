package ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryCustom {
    void removeBookById(String id);
}
