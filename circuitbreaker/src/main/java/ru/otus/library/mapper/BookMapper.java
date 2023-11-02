package ru.otus.library.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookDto;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final AuthorMapper authorMapper;

    private final CategoryMapper categoryMapper;

    public BookDto toDto(@NotNull Book entity) {
        BookDto.BookDtoBuilder bookDto = BookDto.builder();

        bookDto.id(entity.getId());
        bookDto.title(entity.getTitle());
        bookDto.authors(authorMapper.toDtoList(entity.getAuthors()));
        bookDto.categories(categoryMapper.toDtoList(entity.getCategories()));

        return bookDto.build();
    }

    public Book toDomain(@NotNull BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthors(authorMapper.toDomainList(dto.getAuthors()));
        book.setCategories(categoryMapper.toDomainList(dto.getCategories()));
        return book;
    }
}
