package ru.otus.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookDto;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "authors", source = "authors")
    @Mapping(target = "categories", source = "categories")
    BookDto bookToBookDto(Book book);

    @Mapping(target = "authors", source = "authors")
    @Mapping(target = "categories", source = "categories")
    Book bookDtoToBook(BookDto bookDto);
}
