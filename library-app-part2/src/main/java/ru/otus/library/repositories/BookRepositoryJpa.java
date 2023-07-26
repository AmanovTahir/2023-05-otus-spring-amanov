package ru.otus.library.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book save(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Optional<Book> update(Book book) {
        Book updatedBook = entityManager.merge(book);
        return Optional.ofNullable(updatedBook);
    }

    @Override
    public Optional<Book> findById(long id) {
        Book book = entityManager.find(Book.class, id);
        Hibernate.initialize(book.getCategories());
        Hibernate.initialize(book.getAuthors());
        return Optional.of(book);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        books.forEach(book -> {
            Hibernate.initialize(book.getCategories());
            Hibernate.initialize(book.getAuthors());
        });
        return books;
    }

    @Override
    public void delete(long id) {
        Book book = entityManager.find(Book.class, id);
        if (book != null) {
            entityManager.remove(book);
        }
    }
}
