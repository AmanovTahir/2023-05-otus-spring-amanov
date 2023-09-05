package ru.otus.library.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.mapper.AuthorMapper;
import ru.otus.library.services.AuthorService;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorHandler {

    private final AuthorService service;

    private final AuthorMapper authorMapper;

    public List<AuthorDto> getAll() {
        List<Author> all = service.getAll();
        return authorMapper.toDtoList(all);
    }

    public AuthorDto insert(Author author) {
        Author newAuthor = service.insert(author);
        return authorMapper.toDto(newAuthor);
    }
}
