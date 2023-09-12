package ru.otus.library.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.domain.Comment;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.dto.BookDto;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.repositories.AuthorRepository;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.repositories.CategoryRepository;
import ru.otus.library.repositories.CommentRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@DisplayName("BookController должен")
@WebFluxTest(BookController.class)
@ComponentScan("ru.otus.library.mapper")
class BookControllerTest {

    private final List<Book> books = new ArrayList<>();
    @Autowired
    private WebTestClient webClient;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        Author author = new Author("id", "firstName", "lastName");
        Category category = new Category("id", "category");

        books.addAll(List.of(
                new Book("title", List.of(author), List.of(category)),
                new Book("title2", List.of(author), List.of(category))
        ));

    }

    @Test
    public void shouldGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Flux.fromIterable(books));

        var client = webClient.mutate().responseTimeout(Duration.ofSeconds(20)).build();

        //when
        client
                .get().uri("/api/book")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].title").isEqualTo("title")
                .jsonPath("$[1].title").isEqualTo("title2");
    }


    @Test
    public void shouldGetBookById() {
        //given
        var bookId = "bookId";
        var book = books.get(0);
        book.setId(bookId);

        List<Comment> comments = List.of(
                new Comment("id", "comment", book),
                new Comment("id", "comment2", book)
        );

        when(bookRepository.findById(bookId)).thenReturn(Mono.just(book));
        when(commentRepository.findAllByBookId(bookId)).thenReturn(Flux.fromIterable(comments));

        var client = webClient.mutate().responseTimeout(Duration.ofSeconds(20)).build();

        //when
        client.get()
                .uri("/api/book/{bookId}", bookId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.title").isEqualTo("title")
                .jsonPath("$.id").isEqualTo("bookId")
                .jsonPath("$.authors").isArray()
                .jsonPath("$.categories").isArray()
                .jsonPath("$.comments").isArray();
    }

    @Test
    public void shouldSaveBook() {
        //given
        String categoryId = "categoryId";
        String authorId = "authorId";
        String commentId = "commentId";
        BookDto bookDto = new BookDto("new book", Collections.emptyList(), Collections.emptyList());
        Book book = new Book("new book", Collections.emptyList(), Collections.emptyList());
        Category category = new Category(categoryId, "category");
        Author author = new Author(authorId, "first_name", "last_name");
        Comment comment = new Comment(commentId, "comment", book);

        var client = webClient.mutate().responseTimeout(Duration.ofSeconds(20)).build();

        when(authorRepository.findAllById(List.of(authorId))).thenReturn(Flux.just(author));
        when(categoryRepository.findAllById(List.of(categoryId))).thenReturn(Flux.just(category));
        when(commentRepository.findAllByBookId(any())).thenReturn(Flux.just(comment));
        when(commentRepository.save(comment)).thenReturn(Mono.just(comment));
        when(bookRepository.save(any())).thenReturn(Mono.just(book));

        //when
        client.post()
                .uri("/api/book/")
                .body(BodyInserters.fromValue(bookDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.authors").isArray()
                .jsonPath("$.categories").isArray()
                .jsonPath("$.title").isEqualTo(bookDto.getTitle());
    }

    @Test
    public void shouldDeleteBook() {
        //given
        String bookId = "bookId";
        String commentId = "commentId";
        Book book = new Book("new book", Collections.emptyList(), Collections.emptyList());
        Comment comment = new Comment(commentId, "comment", book);

        when(commentRepository.findAllByBookId(bookId)).thenReturn(Flux.just(comment));
        when(bookRepository.findById(bookId)).thenReturn(Mono.just(book));
        when(commentRepository.delete(comment)).thenReturn(Mono.when());
        when(bookRepository.deleteById(bookId)).thenReturn(Mono.when());

        var client = webClient.mutate().responseTimeout(Duration.ofSeconds(20)).build();

        //when
        client.delete()
                .uri("/api/book/{bookId}", bookId)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    public void shouldUpdateBookById() {
        //given
        var bookId = "bookId";
        var bookDto = new BookDto("new book",
                List.of(new AuthorDto("id", "firstName", "lastName")),
                List.of(new CategoryDto("id", "category")));

        var book = new Book(bookId, "new book",
                List.of(new Author("id", "firstName", "lastName")),
                List.of(new Category("id", "category")));
        String categoryId = "categoryId";
        String authorId = "authorId";
        String commentId = "commentId";
        Category category = new Category(categoryId, "category");
        Author author = new Author(authorId, "first_name", "last_name");
        Comment comment = new Comment(commentId, "comment", book);


        when(bookRepository.findById(bookId)).thenReturn(Mono.just(book));
        when(authorRepository.findAllById(any(Mono.class))).thenReturn(Flux.just(author));
        when(categoryRepository.findAllById(any(Mono.class))).thenReturn(Flux.just(category));
        when(commentRepository.findAllByBookId(any())).thenReturn(Flux.just(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(Mono.just(comment));
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(book));

        var client = webClient.mutate().responseTimeout(Duration.ofSeconds(20)).build();

        //when
        client.put()
                .uri("/api/book/{bookId}", bookId)
                .body(BodyInserters.fromValue(bookDto))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.title").isEqualTo("new book")
                .jsonPath("$.id").isEqualTo("bookId")
                .jsonPath("$.authors").isArray()
                .jsonPath("$.categories").isArray();
    }

}
