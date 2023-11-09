package ru.otus.library.services.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.exceptions.BookNotFoundException;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.services.BookService;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    @CircuitBreaker(name = "bookServiceCircuitBreaker")
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "bookServiceCircuitBreaker", fallbackMethod = "getAllBookFallback")
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "bookServiceCircuitBreaker", fallbackMethod = "getBookFallback")
    public Book getById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found!"));
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "bookServiceCircuitBreaker")
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "bookServiceCircuitBreaker")
    public Book insert(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @CircuitBreaker(name = "bookServiceCircuitBreaker")
    public long getBookCount() {
        return bookRepository.count();
    }

    public List<Book> getAllBookFallback(Throwable e) {
        log.info("fallbackList method called: {}", e.getMessage());
        return List.of(new Book("N/A",
                List.of(new Author("N/A", "N/A")),
                List.of(new Category("N/A"))));
    }

    public Book getBookFallback(Throwable e) {
        log.info("fallbackBook method called: {}", e.getMessage());
        return new Book("N/A", Collections.emptyList(), Collections.emptyList());
    }
}
