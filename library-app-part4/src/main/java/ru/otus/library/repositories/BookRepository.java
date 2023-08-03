package ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByAuthorsContains(Author author);

    Optional<Book> findByCategoriesContains(Category Category);

    void removeAuthorsArrayElementsById(String id);

}
