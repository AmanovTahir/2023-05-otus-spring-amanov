package ru.otus.library.mapper;

import org.mapstruct.Mapper;
import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorDto;

@Mapper(config = MappingConfig.class)
public interface AuthorMapper extends MappingConfig<AuthorDto, Author> {

}
