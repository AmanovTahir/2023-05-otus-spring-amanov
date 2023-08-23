package ru.otus.library.mapper;

import org.mapstruct.Mapper;
import ru.otus.library.domain.Comment;
import ru.otus.library.dto.CommentDto;

@Mapper(config = MappingConfig.class)
public interface CommentMapper extends MappingConfig<CommentDto, Comment> {
}
