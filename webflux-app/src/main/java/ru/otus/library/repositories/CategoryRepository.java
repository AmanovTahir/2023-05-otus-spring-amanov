package ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.library.domain.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

}
