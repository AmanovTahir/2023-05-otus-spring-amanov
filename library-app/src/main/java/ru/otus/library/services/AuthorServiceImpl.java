package ru.otus.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.domain.Author;
import ru.otus.library.exception.DataNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    @Transactional
    public void deleteById(long id) {
        authorDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(long id) {
        return authorDao.getById(id).orElseThrow(() -> new DataNotFoundException("Автор не найден!"));
    }

    @Override
    @Transactional
    public Author update(Author author) {
        return authorDao.update(author).orElseThrow();
    }

    @Override
    @Transactional
    public Author insert(Author author) {
        return authorDao.insert(author).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public int count() {
        return authorDao.count();
    }
}
