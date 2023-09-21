package ru.otus.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.library.config.SecurityConfig;
import ru.otus.library.dto.BookDto;
import ru.otus.library.handlers.BookHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
@DisplayName("BookController должен")
@Import(SecurityConfig.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookHandler handler;

    @ParameterizedTest
    @CsvSource({
            "/api/book/,GET,ADMIN",
            "/api/book/123,GET,ADMIN",
            "/api/book/,POST,ADMIN",
            "/api/book/123,PUT,ADMIN",
            "/api/book/123,DELETE,ADMIN",

            "/api/book/,GET, USER",
            "/api/book/123,GET, USER",
            "/api/book/,POST, USER",
            "/api/book/123,PUT, USER",
            "/api/book/123,DELETE, USER"
    })
    void shouldAccessEndpoint(String endpoint, String method, String role) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = getRequestBuilder(endpoint, method);

        if (role.equals("ADMIN")) {
            mvc.perform(requestBuilder.with(user("admin").password("1").roles("ADMIN")))
                    .andDo(print())
                    .andExpect(status().isOk());
        } else {
            mvc.perform(requestBuilder.with(user("user").password("1").roles("USER")))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
        }
    }

    @SneakyThrows
    private static MockHttpServletRequestBuilder getRequestBuilder(String endpoint, String method) {
        BookDto dto = BookDto.builder().id(1L).title("Sample Book").build();
        return switch (method) {
            case "GET" -> MockMvcRequestBuilders.get(endpoint);
            case "POST" -> MockMvcRequestBuilders.post(endpoint)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(dto));
            case "PUT" -> MockMvcRequestBuilders.put(endpoint)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(dto));
            case "DELETE" -> MockMvcRequestBuilders.delete(endpoint);
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        };
    }
}
