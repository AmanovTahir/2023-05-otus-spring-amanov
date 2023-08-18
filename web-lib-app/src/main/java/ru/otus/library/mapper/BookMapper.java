package ru.otus.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookDto;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "authors", source = "authors")
    @Mapping(target = "categories", source = "categories")
    @Mapping(target = "id", source = "id")
    BookDto bookToBookDto(Book book);

    @Mapping(target = "authors", source = "authors")
    @Mapping(target = "categories", source = "categories")
    @Mapping(target = "id", source = "id")
    Book bookDtoToBook(BookDto bookDto);
}
