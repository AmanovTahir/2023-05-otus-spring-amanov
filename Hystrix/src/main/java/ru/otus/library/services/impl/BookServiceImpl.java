package ru.otus.library.services.impl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;
import ru.otus.library.exceptions.BookNotFoundException;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.services.BookService;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @SneakyThrows
    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(String id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book not found!"));
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book insert(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public long getBookCount() {
        return bookRepository.count();
    }
}
