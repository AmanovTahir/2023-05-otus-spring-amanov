package ru.otus.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.dto.BookDto;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.dto.CommentDto;
import ru.otus.library.handlers.AuthorHandler;
import ru.otus.library.handlers.BookHandler;
import ru.otus.library.handlers.CategoryHandler;
import ru.otus.library.services.AuthorService;
import ru.otus.library.services.BookService;
import ru.otus.library.services.CategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BookController.class)
@DisplayName("BookController должен")
@TestPropertySource(properties = "mongock.enabled=false")
class BookControllerTest {

    private final List<BookDto> books = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookHandler bookHandler;

    @MockBean
    private AuthorHandler authorHandler;

    @MockBean
    private CategoryHandler categoryHandler;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private BookService bookService;


    private static HashMap<Integer, AuthorDto> getAuthorMap() {
        return new HashMap<>() {{
            put(1, new AuthorDto("Мария", "Иванова"));
            put(2, new AuthorDto("Алексей", "Петрович"));
        }};
    }

    private static HashMap<Integer, CategoryDto> getCategoryMap() {
        return new HashMap<>() {{
            put(1, new CategoryDto("Научная фантастика"));
            put(2, new CategoryDto("Фэнтези"));
        }};
    }

    @BeforeEach
    void setUp() {
        Map<Integer, AuthorDto> authorMap = getAuthorMap();
        Map<Integer, CategoryDto> categoryMap = getCategoryMap();

        books.add(new BookDto("Звездный Охотник", List.of(authorMap.get(1)), List.of(categoryMap.get(1))));
        books.add(new BookDto("Путь Меча", List.of(authorMap.get(2)), List.of(categoryMap.get(2))));

        List<CommentDto> comments = getComments();
        books.forEach(dto -> {
            List<CommentDto> filteredComments = comments.stream()
                    .filter(comment -> comment.getBook().getId().equals(dto.getId()))
                    .collect(toList());
            dto.setComments(filteredComments);
        });
    }

    @Test
    @DisplayName("вернуть все книги")
    void shouldGetAllBooks() throws Exception {
        when(bookHandler.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/list"))
                .andExpect(model().attribute("books", hasSize(2)));
    }

    @Test
    @DisplayName("удалить книгу")
    void shouldDeleteBook() throws Exception {
        String bookId = "1";

        mockMvc.perform(post("/books/delete").param("id", bookId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        verify(bookHandler).deleteBook(bookId);
    }

    @Test
    @DisplayName("обновить книгу")
    void shouldUpdateBook() throws Exception {
        String bookId = "id";

        BookDto bookDto = new BookDto();
        List<AuthorDto> authors = new ArrayList<>();
        List<CategoryDto> categories = new ArrayList<>();

        when(bookHandler.getBook(bookId)).thenReturn(bookDto);
        when(authorHandler.getAll()).thenReturn(authors);
        when(categoryHandler.getAll()).thenReturn(categories);

        mockMvc.perform(get("/books/edit").param("id", bookId))
                .andExpect(status().isOk())
                .andExpect(view().name("book/edit"))
                .andExpect(model().attribute("book", equalTo(bookDto)))
                .andExpect(model().attribute("authors", equalTo(authors)))
                .andExpect(model().attribute("categories", equalTo(categories)));

        mockMvc.perform(post("/books/update"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

    }

    @Test
    @DisplayName("добавить книгу")
    void shouldAddPage() throws Exception {
        List<AuthorDto> authors = new ArrayList<>();
        List<CategoryDto> categories = new ArrayList<>();

        when(authorHandler.getAll()).thenReturn(authors);
        when(categoryHandler.getAll()).thenReturn(categories);

        mockMvc.perform(get("/books/add"))
                .andExpect(status().isOk())
                .andExpect(view().name(
                        "book/add"))
                .andExpect(model().attribute("authors", equalTo(authors)))
                .andExpect(model().attribute("categories", equalTo(categories)));

        mockMvc.perform(post("/books/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    @DisplayName("показать содержимое книги")
    void viewPage() throws Exception {
        String bookId = "id";
        BookDto bookDto = new BookDto("simple book");

        when(bookHandler.getBook(bookId)).thenReturn(bookDto);

        mockMvc.perform(get("/books/book").param("id", bookId))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", equalTo(bookDto)))
                .andExpect(view().name("book/view"));
    }

    private ArrayList<CommentDto> getComments() {
        return new ArrayList<>() {{
            new CommentDto("Увлекательная научная фантастика, читал на одном дыхании!", books.get(0));
            new CommentDto("Персонажи такие живые, что кажется, будто сам в космосе путешествуешь!", books.get(0));
            new CommentDto("Очарован миром фэнтези от Алексея Петровича!", books.get(1));
            new CommentDto("Эпические сражения и герои, которые остаются в памяти навсегда!", books.get(1));
        }};
    }
}
