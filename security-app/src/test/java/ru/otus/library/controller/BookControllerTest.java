package ru.otus.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.library.appConfig.SecurityConfig;
import ru.otus.library.dto.BookDto;
import ru.otus.library.handlers.BookHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    @ParameterizedTest
    @CsvSource({
            "/api/book/,GET,true",
            "/api/book/123,GET,true",
            "/api/book/,POST,true",
            "/api/book/123,PUT,true",
            "/api/book/123,DELETE,true",

            "/api/book/,GET,false",
            "/api/book/123,GET,false",
            "/api/book/,POST,false",
            "/api/book/123,PUT,false",
            "/api/book/123,DELETE,false"
    })
    void shouldAccessEndpoint(String endpoint, String method, boolean withUser) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = getRequestBuilder(endpoint, method);

        if (withUser) {
            mvc.perform(requestBuilder.with(user("user").password("password")))
                    .andDo(print())
                    .andExpect(status().isOk());
        } else {
            mvc.perform(requestBuilder)
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
        }
    }

    private static MockHttpServletRequestBuilder getRequestBuilder(String endpoint, String method) throws JsonProcessingException {
        BookDto dto = BookDto.builder().id("123").title("Sample Book").build();
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
