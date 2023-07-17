package ru.otus.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dao.BookDao;
import ru.otus.library.domain.Book;
import ru.otus.library.exception.DataNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    @Transactional
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(long id) {
        return bookDao.getById(id).orElseThrow(() -> new DataNotFoundException("Книга не найдена!"));
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return bookDao.update(book).orElseThrow();
    }

    @Override
    @Transactional
    public Book insert(Book book) {
        return bookDao.insert(book).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public int count() {
        return bookDao.count();
    }
}
