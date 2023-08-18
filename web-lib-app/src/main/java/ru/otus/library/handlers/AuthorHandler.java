package ru.otus.library.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.mapper.AuthorMapper;
import ru.otus.library.services.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorHandler {

    private final AuthorService service;

    private final AuthorMapper authorMapper;

    public List<AuthorDto> getAll() {
        return service.getAll().stream()
                .map(authorMapper::authorToAuthorDto)
                .collect(Collectors.toList());
    }

    public AuthorDto insert(Author author) {
        Author newAuthor = service.insert(author);
        return authorMapper.authorToAuthorDto(newAuthor);
    }
}
