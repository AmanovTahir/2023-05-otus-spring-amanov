package ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Author;


public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Flux<Author> findAllById(Mono<String> id);
}


