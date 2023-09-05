package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.library.dto.BookDto;
import ru.otus.library.handlers.BookHandler;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
@Slf4j
public class BookController {

    private final BookHandler bookHandler;

    @GetMapping("/")
    @Cacheable(value = "bookCache")
    public List<BookDto> getAllBooks() {
        return bookHandler.getAllBooks();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "bookPostCache")
    public BookDto getBook(@PathVariable String id) {
        return bookHandler.getBook(id);
    }

    @PostMapping("/")
    public BookDto addBook(@RequestBody BookDto dto) {
        return bookHandler.addBook(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        bookHandler.deleteBook(id);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = {"bookPostCache", "bookCache"}, allEntries = true)
    public BookDto editBook(@PathVariable String id, @RequestBody BookDto dto) {
        dto.setId(id);
        return bookHandler.updateBook(dto);
    }
}
