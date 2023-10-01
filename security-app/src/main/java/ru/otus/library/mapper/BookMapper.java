package ru.otus.library.mapper;

import org.mapstruct.Mapper;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookDto;

@Mapper(config = MappingConfig.class, uses = {
        AuthorMapper.class,
        CategoryMapper.class,
        CommentMapper.class
})
public interface BookMapper extends MappingConfig<BookDto, Book> {

}
