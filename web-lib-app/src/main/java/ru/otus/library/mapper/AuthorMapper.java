package ru.otus.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorDto;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "id", source = "id")
    AuthorDto authorToAuthorDto(Author author);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "id", source = "id")
    Author authorDtoToAuthor(AuthorDto authorDto);
}
