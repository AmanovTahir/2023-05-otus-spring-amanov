package ru.otus.library.repositories;

import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author save(Author author);

    Optional<Author> update(Author author);

    Optional<Author> findById(long id);

    List<Author> findAll();

    void delete(long id);
}
