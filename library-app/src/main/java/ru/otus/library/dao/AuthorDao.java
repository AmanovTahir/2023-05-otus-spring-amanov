package ru.otus.library.dao;

import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    int count();

    Optional<Author> insert(Author author);

    Optional<Author> update(Author author);

    Optional<Author> getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
