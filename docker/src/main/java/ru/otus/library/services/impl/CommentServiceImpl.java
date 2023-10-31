package ru.otus.library.services.impl;

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
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllByBookId(String bookId) {
        List<Comment> allByBookId = repository.findAllByBookId(bookId);
        return allByBookId.stream().map(commentMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(String id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        return repository.save(comment);
    }

    @Override
    @Transactional
    public Comment insert(Comment comment) {
        return repository.save(comment);
    }
}
