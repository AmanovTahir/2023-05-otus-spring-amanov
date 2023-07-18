package ru.otus.library.services;

import ru.otus.library.domain.Book;

import java.util.List;

public interface BookService {
    void deleteById(long id);

    List<Book> getAll();

    Book getById(long id);

    Book update(Book book);

    Book insert(Book book);

    int count();
}
