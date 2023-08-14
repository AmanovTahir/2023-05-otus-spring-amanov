package ru.otus.library.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Comment;
import ru.otus.library.repositories.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    @Transactional
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllByBookId(String bookId) {
        return repository.findAllByBookId(bookId);
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
