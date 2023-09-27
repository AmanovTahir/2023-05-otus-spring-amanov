package ru.otus.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.otus.library.config.SecurityConfig;
import ru.otus.library.dto.BookDto;
import ru.otus.library.handlers.BookHandler;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("provideTestData")
    @SneakyThrows
    void shouldAccessEndpoint(String endpoint, HttpMethod method, String role, BookDto dto, int expectedStatus) {
        mvc.perform(buildRequest(endpoint, method, role, dto))
                .andDo(print())
                .andExpect(status().is(expectedStatus));
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                of("/api/book/", HttpMethod.GET, "ADMIN", null, 200),
                of("/api/book/123", HttpMethod.GET, "ADMIN", null, 200),
                of("/api/book/", HttpMethod.POST, "ADMIN", createBookDto(), 200),
                of("/api/book/123", HttpMethod.PUT, "ADMIN", createBookDto(), 200),
                of("/api/book/123", HttpMethod.DELETE, "ADMIN", null, 200),

                of("/api/book/", HttpMethod.GET, "USER", null, 200),
                of("/api/book/123", HttpMethod.GET, "USER", null, 200),
                of("/api/book/", HttpMethod.POST, "USER", createBookDto(), 403),
                of("/api/book/123", HttpMethod.PUT, "USER", createBookDto(), 403),
                of("/api/book/123", HttpMethod.DELETE, "USER", null, 403),

                of("/api/book/", HttpMethod.GET, "OTHER_ROLE", null, 403),
                of("/api/book/123", HttpMethod.GET, "OTHER_ROLE", null, 403),
                of("/api/book/", HttpMethod.POST, "OTHER_ROLE", createBookDto(), 403),
                of("/api/book/123", HttpMethod.PUT, "OTHER_ROLE", createBookDto(), 403),
                of("/api/book/123", HttpMethod.DELETE, "OTHER_ROLE", null, 403)
        );
    }

    @SneakyThrows
    private MockHttpServletRequestBuilder buildRequest(String endpoint, HttpMethod method, String role, BookDto dto) {
        MockHttpServletRequestBuilder requestBuilder;
        switch (method) {
            case GET -> requestBuilder = get(endpoint);
            case POST -> requestBuilder = post(endpoint)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto));
            case PUT -> requestBuilder = put(endpoint)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto));
            case DELETE -> requestBuilder = delete(endpoint);
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
        return requestBuilder.with(user(role).roles(role));
    }

    private static BookDto createBookDto() {
        return BookDto.builder().id(1L).title("Sample Book").build();
    }

    private enum HttpMethod {
        GET, POST, PUT, DELETE
    }
}
