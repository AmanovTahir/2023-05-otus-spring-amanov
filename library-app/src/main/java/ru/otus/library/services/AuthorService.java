package ru.otus.library.services;

import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorService {

    void deleteById(long id);

    List<Author> getAll();

    Author getById(long id);

    Author update(Author author);

    Author insert(Author author);

    int count();
}
