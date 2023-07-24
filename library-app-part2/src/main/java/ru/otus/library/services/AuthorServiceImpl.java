package ru.otus.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Author;
import ru.otus.library.repositories.AuthorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public void deleteById(long id) {
        authorRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Author update(Author author) {
        return authorRepository.update(author).orElseThrow();
    }

    @Override
    @Transactional
    public Author insert(Author author) {
        return authorRepository.save(author);
    }

}
