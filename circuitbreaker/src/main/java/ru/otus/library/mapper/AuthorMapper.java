package ru.otus.library.mapper;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorDto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorMapper {
    public AuthorDto toDto(@NotNull Author entity) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(entity.getId());
        authorDto.setFirstName(entity.getFirstName());
        authorDto.setLastName(entity.getLastName());
        return authorDto;
    }

    public List<AuthorDto> toDtoList(@NotNull List<Author> entities) {
        List<AuthorDto> list = new ArrayList<>(entities.size());
        for (Author author : entities) {
            list.add(toDto(author));
        }
        return list;
    }

    public Author toDomain(@NotNull AuthorDto dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        return author;
    }

    public List<Author> toDomainList(@NotNull List<AuthorDto> dtos) {
        List<Author> list = new ArrayList<>(dtos.size());
        for (AuthorDto dto : dtos) {
            list.add(toDomain(dto));
        }
        return list;
    }
}
