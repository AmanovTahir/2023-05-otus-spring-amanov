package ru.otus.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.library.appConfig.SecurityConfig;
import ru.otus.library.dto.BookDto;
import ru.otus.library.handlers.BookHandler;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
@DisplayName("BookController должен")
@TestPropertySource(properties = "mongock.enabled=false")
@Import(SecurityConfig.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookHandler handler;

    @Test
    @DisplayName("вернуть все книги")
    @WithMockUser
    void shouldGetAllBooks() throws Exception {
        when(handler.getAllBooks()).thenReturn(Collections.emptyList());

        mvc.perform(get("/api/book/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @DisplayName("перенаправить запрос на /login")
    void shouldRedirectRequestToLogin() throws Exception {
        when(handler.getAllBooks()).thenReturn(Collections.emptyList());

        mvc.perform(get("/api/book/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("удалить книгу")
    @WithMockUser
    void shouldDeleteBook() throws Exception {
        mvc.perform(delete("/api/book/123"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("обновить книгу")
    @WithMockUser
    void shouldUpdateBook() throws Exception {
        BookDto dto = BookDto.builder().id("123").title("Sample Book").build();

        when(handler.updateBook(dto)).thenReturn(dto);

        mvc.perform(MockMvcRequestBuilders.put("/api/book/123")
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Book"))
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @DisplayName("добавить книгу")
    @WithMockUser
    void shouldAddPage() throws Exception {
        BookDto dto = BookDto.builder().id("123").title("Sample Book").build();

        when(handler.addBook(dto)).thenReturn(dto);

        mvc.perform(MockMvcRequestBuilders.post("/api/book/")
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Book"))
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @DisplayName("показать содержимое книги")
    @WithMockUser
    void viewPage() throws Exception {
        BookDto dto = BookDto.builder().id("123").title("Sample Book").build();

        when(handler.getBook(dto.getId())).thenReturn(dto);

        mvc.perform(MockMvcRequestBuilders.get("/api/book/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Book"))
                .andExpect(content().contentType(APPLICATION_JSON));
    }
}
