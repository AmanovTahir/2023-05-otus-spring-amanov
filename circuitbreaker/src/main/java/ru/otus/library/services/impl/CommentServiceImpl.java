package ru.otus.library.services.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Comment;
import ru.otus.library.dto.CommentDto;
import ru.otus.library.mapper.CommentMapper;
import ru.otus.library.repositories.CommentRepository;
import ru.otus.library.services.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    private final CommentMapper commentMapper;

    @Override
    @Transactional
    @CircuitBreaker(name = "commentServiceCircuitBreaker")
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "commentServiceCircuitBreaker")
    public List<CommentDto> getAllByBookId(String bookId) {
        List<Comment> allByBookId = repository.findAllByBookId(bookId);
        return allByBookId.stream().map(commentMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "commentServiceCircuitBreaker")
    public Comment getById(String id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "commentServiceCircuitBreaker")
    public Comment update(Comment comment) {
        return repository.save(comment);
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "commentServiceCircuitBreaker")
    public Comment insert(Comment comment) {
        return repository.save(comment);
    }
}
