package ru.otus.library.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Comment;
import ru.otus.library.repositories.CommentRepository;
import ru.otus.library.services.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllByBookId(Long bookId) {
        return repository.findAllByBookId(bookId);
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

    @Override
    @Transactional
    @PreAuthorize("hasPermission(hasRole('ADMIN'))")
    public void deleteByBookId(Long bookId) {
        repository.deleteAllByBook_Id(bookId);
    }
}
