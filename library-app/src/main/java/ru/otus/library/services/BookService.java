package ru.otus.library.services;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;

import java.util.List;

public interface BookService {
    @Transactional
    void deleteById(long id);

    @Transactional(readOnly = true)
    List<Book> getAll();

    @Transactional(readOnly = true)
    Book getById(long id);

    @Transactional
    Book update(Book book);

    @Transactional
    Book insert(Book book);

    @Transactional(readOnly = true)
    int count();
}
