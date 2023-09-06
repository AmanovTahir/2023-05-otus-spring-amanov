package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.mapper.AuthorMapper;
import ru.otus.library.repositories.AuthorRepository;

@RestController
@RequestMapping("/api/author")
@AllArgsConstructor
@Slf4j
public class AuthorController {

    private final AuthorRepository repository;

    private final AuthorMapper mapper;

    @GetMapping
    public Flux<AuthorDto> getAllAuthors() {
        log.debug("REST requesting all authors");
        return repository.findAll().map(mapper::toDto);
    }
}
