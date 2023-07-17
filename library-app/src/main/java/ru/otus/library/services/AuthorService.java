package ru.otus.library.services;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorService {
    @Transactional
    void deleteById(long id);

    @Transactional(readOnly = true)
    List<Author> getAll();

    @Transactional(readOnly = true)
    Author getById(long id);

    @Transactional
    Author update(Author author);

    @Transactional
    Author insert(Author author);

    @Transactional(readOnly = true)
    int count();
}
