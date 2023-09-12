package ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

    Flux<Category> findAllById(Mono<String> id);
}
