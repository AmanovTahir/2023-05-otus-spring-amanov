package ru.otus.library.services.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
        @CircuitBreaker(name = "authorServiceCircuitBreaker")
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "authorServiceCircuitBreaker")
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "authorServiceCircuitBreaker")
    public Author getById(String id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "authorServiceCircuitBreaker")
    public Author update(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "authorServiceCircuitBreaker")
    public Author insert(Author author) {
        return authorRepository.save(author);
    }

}
