package ru.otus.library.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.services.BookService;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Book insert(Book book) {
        return bookRepository.save(book);
    }
}
