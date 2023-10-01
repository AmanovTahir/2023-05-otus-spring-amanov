package ru.otus.library.services;

import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(Long id);

    Author update(Author author);

    Author insert(Author author);

}
