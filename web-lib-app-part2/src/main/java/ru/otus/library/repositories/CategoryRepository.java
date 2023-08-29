package ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
