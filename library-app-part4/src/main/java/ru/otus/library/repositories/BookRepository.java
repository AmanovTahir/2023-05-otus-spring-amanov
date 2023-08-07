package ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.Book;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    Optional<Book> findByAuthorsId(String authorId);

    Optional<Book> findByCategoriesId(String categoryId);

}
