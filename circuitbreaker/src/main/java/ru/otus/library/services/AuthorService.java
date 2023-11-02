package ru.otus.library.services;

import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorService {

    void deleteById(String id);

    List<Author> getAll();

    Author getById(String id);

    Author update(Author author);

    Author insert(Author author);

}
