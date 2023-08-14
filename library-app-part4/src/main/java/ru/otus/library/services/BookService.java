package ru.otus.library.services;

import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookDto;

import java.util.List;

public interface BookService {
    void deleteById(String id);

    List<BookDto> getAll();

    BookDto getById(String id);

    BookDto update(Book book);

    BookDto insert(Book book);
}
