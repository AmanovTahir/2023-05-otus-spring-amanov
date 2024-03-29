package ru.otus.library.repositories;

import ru.otus.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> update(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void delete(long id);
}
