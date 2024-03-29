package ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.library.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String>, CommentRepositoryCustom {

}
