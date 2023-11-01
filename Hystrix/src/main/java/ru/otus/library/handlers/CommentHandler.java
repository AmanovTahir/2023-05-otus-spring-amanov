package ru.otus.library.handlers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.CommentDto;
import ru.otus.library.services.CommentService;

import java.util.List;

@Service
@AllArgsConstructor
@CircuitBreaker(name = "fallback")
public class CommentHandler {
    private final CommentService commentService;

    public List<CommentDto> getCommentByBookId(String id) {
        return commentService.getAllByBookId(id);
    }
}
