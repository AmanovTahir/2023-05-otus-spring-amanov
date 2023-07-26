package ru.otus.library.services;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;
import ru.otus.library.repositories.BookRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return bookRepository.update(book).orElseThrow();
    }

    @Override
    @Transactional
    public Book insert(Book book) {
        return bookRepository.save(book);
    }

}
