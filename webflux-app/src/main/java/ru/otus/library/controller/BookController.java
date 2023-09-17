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
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.dto.BookDto;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.mapper.BookMapper;
import ru.otus.library.mapper.CommentMapper;
import ru.otus.library.repositories.AuthorRepository;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.repositories.CategoryRepository;
import ru.otus.library.repositories.CommentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
@Slf4j
public class BookController {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    private final AuthorRepository authorRepository;

    private final CategoryRepository categoryRepository;

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
        return fetchAuthorsAndCategoriesAndSave(dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        log.debug("REST requesting delete book by id: {}", id);
        return bookRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Book with id: " + id + " not found")))
                .flatMap(book -> commentRepository.deleteAllBookById(id)
                        .then(bookRepository.deleteById(id)))
                .then();
    }

    @PutMapping("/{id}")
    public Mono<BookDto> editBook(@PathVariable String id, @RequestBody BookDto dto) {
        log.debug("REST requesting edit book: {}", dto);
        return bookRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Book with id: " + id + " not found")))
                .then(fetchAuthorsAndCategoriesAndSave(dto));
    }

    private Mono<BookDto> fetchAuthorsAndCategoriesAndSave(BookDto dto) {
        List<String> categoryIds = dto.getCategories().stream().map(CategoryDto::getId).toList();
        List<String> authorsIds = dto.getAuthors().stream().map(AuthorDto::getId).toList();

        Flux<Author> authorsFlux = Flux.fromIterable(authorsIds)
                .flatMap(authorId -> authorRepository.findAllById(Mono.just(authorId)))
                .collectList()
                .flatMapMany(Flux::fromIterable);

        Flux<Category> categoriesFlux = Flux.fromIterable(categoryIds)
                .flatMap(categoryId -> categoryRepository.findAllById(Mono.just(categoryId)))
                .collectList()
                .flatMapMany(Flux::fromIterable);

        return Flux.zip(authorsFlux.collectList(), categoriesFlux.collectList())
                .flatMap(tuple -> saveBook(dto, tuple.getT1(),  tuple.getT2()))
                .next();
    }

    private Mono<BookDto> saveBook(BookDto dto, List<Author> authors, List<Category> categories) {
        Book book = bookMapper.toDomain(dto);
        book.setAuthors(authors);
        book.setCategories(categories);

        return commentRepository.addBookToComments(book)
                .then(bookRepository.save(book).map(bookMapper::toDto));
    }
}
