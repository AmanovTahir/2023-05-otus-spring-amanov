package ru.otus.library.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Author;
import ru.otus.library.repositories.AuthorRepository;
import ru.otus.library.services.AuthorService;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(String id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Author update(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author insert(Author author) {
        return authorRepository.save(author);
    }

}
