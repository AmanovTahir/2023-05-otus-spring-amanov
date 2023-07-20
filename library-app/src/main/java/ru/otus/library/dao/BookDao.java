package ru.otus.library.dao;

import ru.otus.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    int count();

    Optional<Book> insert(Book book);

    Optional<Book> update(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
