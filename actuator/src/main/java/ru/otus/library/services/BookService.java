package ru.otus.library.services;

import ru.otus.library.domain.Book;

import java.util.List;

public interface BookService {
    void deleteById(String id);

    List<Book> getAll();

    Book getById(String id);

    Book update(Book book);

    Book insert(Book book);

    long getBookCount();
}
