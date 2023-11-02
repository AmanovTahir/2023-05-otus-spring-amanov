package ru.otus.library.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.CommentDto;
import ru.otus.library.services.CommentService;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentHandler {
    private final CommentService commentService;

    public List<CommentDto> getCommentByBookId(String id) {
        return commentService.getAllByBookId(id);
    }
}
