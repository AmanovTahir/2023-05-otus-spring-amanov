package ru.otus.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.library.domain.Comment;
import ru.otus.library.dto.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "text", source = "text")
    @Mapping(target = "book", source = "book")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(target = "text", source = "text")
    @Mapping(target = "book", source = "book")
    Comment commentDtoToComment(CommentDto commentDto);
}
