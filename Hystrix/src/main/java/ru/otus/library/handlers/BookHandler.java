package ru.otus.library.handlers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.dto.BookDto;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.dto.CommentDto;
import ru.otus.library.mapper.BookMapper;
import ru.otus.library.services.BookService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookHandler {

    private final BookService bookService;

    private final CommentHandler commentHandler;

    private final BookMapper bookMapper;


    @CircuitBreaker(name = "fallback")
    public BookDto addBook(BookDto dto) {
        Book book = bookMapper.toDomain(dto);
        return bookMapper.toDto(bookService.insert(book));
    }

    @CircuitBreaker(name = "fallback")
    public BookDto updateBook(BookDto dto) {
        Book domain = bookMapper.toDomain(dto);
        return bookMapper.toDto(bookService.update(domain));
    }

    @SneakyThrows
    @CircuitBreaker(name = "fallback", fallbackMethod = "fallbackBook")
    public BookDto getBook(String id) {
        Book book = bookService.getById(id);
        List<CommentDto> comments = commentHandler.getCommentByBookId(id);

        BookDto bookDto = bookMapper.toDto(book);
        bookDto.setComments(comments);

        return bookDto;
    }

    @SneakyThrows
    @CircuitBreaker(name = "fallback", fallbackMethod = "fallbackList")
    public List<BookDto> getAllBooks() {
        return bookService.getAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @CircuitBreaker(name = "fallback")
    public void deleteBook(String id) {
        bookService.deleteById(id);
    }

    public List<BookDto> fallbackList(Throwable e) {
        log.info("fallbackList method called: {}", e.getMessage());
        return List.of(new BookDto("N/A",
                List.of(new AuthorDto("N/A", "N/A")),
                List.of(new CategoryDto("N/A"))));
    }

    public BookDto fallbackBook(Throwable e) {
        log.info("fallbackBook method called: {}", e.getMessage());
        return new BookDto("N/A", Collections.emptyList(), Collections.emptyList());
    }
}
