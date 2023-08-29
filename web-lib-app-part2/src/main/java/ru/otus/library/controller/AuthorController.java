package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.handlers.AuthorHandler;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthorController {

    private final AuthorHandler handler;

    @GetMapping("/")
    public List<AuthorDto> getAllAuthors() {
        return handler.getAll();
    }
}
