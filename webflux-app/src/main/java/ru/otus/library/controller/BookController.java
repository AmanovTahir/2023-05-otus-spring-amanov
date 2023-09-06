package ru.otus.library.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookDto;
import ru.otus.library.mapper.BookMapper;
import ru.otus.library.mapper.CommentMapper;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.repositories.CommentRepository;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
@Slf4j
public class BookController {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    private final BookMapper bookMapper;

    private final CommentMapper commentMapper;

    @GetMapping
    public Flux<BookDto> getAllBooks() {
        log.debug("REST requesting all books");
        return bookRepository.findAll().map(bookMapper::toDto);
    }

    @GetMapping(value = "/{id}")
    public Mono<BookDto> getBook(@PathVariable String id) {
        log.debug("REST requesting book by id: {}", id);
        return bookRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Book with id: " + id + " not found")))
                .map(bookMapper::toDto)
                .flatMap(dto -> commentRepository.findAllByBookId(dto.getId()).collectList()
                        .map(comments -> {
                            dto.setComments(commentMapper.toDtoList(comments));
                            return dto;
                        }));
    }

    @PostMapping("/")
    public Mono<BookDto> addBook(@RequestBody BookDto dto) {
        log.debug("REST requesting save book: {}", dto);
        return bookRepository.save(bookMapper.toDomain(dto)).map(bookMapper::toDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        log.debug("REST requesting delete book by id: {}", id);
        return bookRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Book with id: " + id + " not found")))
                .flatMap(book -> bookRepository.deleteById(id))
                .then();
    }

    @PutMapping("/{id}")
    public Mono<BookDto> editBook(@PathVariable String id, @RequestBody BookDto dto) {
        log.debug("REST requesting edit book: {}", dto);
        Book book = bookMapper.toDomain(dto);
        book.setId(id);
        return bookRepository.save(book).map(bookMapper::toDto);
    }
}
