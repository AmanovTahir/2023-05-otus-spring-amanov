package ru.otus.library.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Author> findById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    public Author save(Author author) {
        if (author.getId() == 0) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }

    public Optional<Author> update(Author author) {
        Author updatedAuthor = entityManager.merge(author);
        return Optional.ofNullable(updatedAuthor);
    }


    public void delete(long id) {
        Author author = entityManager.find(Author.class, id);
        if (author != null) {
            entityManager.remove(author);
        }
    }
}
