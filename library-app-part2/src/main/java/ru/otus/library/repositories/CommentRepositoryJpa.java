package ru.otus.library.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public Optional<Comment> update(Comment comment) {
        Comment updatedComment = entityManager.merge(comment);
        return Optional.ofNullable(updatedComment);
    }

    @Override
    public Optional<Comment> findById(long id) {
        Comment comment = entityManager.find(Comment.class, id);
        return Optional.ofNullable(comment);
    }

    @Override
    public List<Comment> findAllByBookId(long bookId) {
        return entityManager.createQuery("SELECT c FROM Comment c WHERE c.book.id = :bookId", Comment.class)
                .setParameter("bookId", bookId)
                .getResultList();
    }

    @Override
    public void delete(long id) {
        Comment comment = entityManager.find(Comment.class, id);
        if (comment != null) {
            entityManager.remove(comment);
        }
    }
}
