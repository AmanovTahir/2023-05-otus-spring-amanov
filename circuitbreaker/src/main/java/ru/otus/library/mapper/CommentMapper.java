package ru.otus.library.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Comment;
import ru.otus.library.dto.CommentDto;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final BookMapper bookMapper;

    public CommentDto toDto(@NotNull Comment entity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(entity.getId());
        commentDto.setText(entity.getText());
        commentDto.setBook(bookMapper.toDto(entity.getBook()));
        return commentDto;
    }
}
