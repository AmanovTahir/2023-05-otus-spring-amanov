package ru.otus.library.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Category;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryJpa implements CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category save(Category category) {
        if (category.getId() == 0) {
            entityManager.persist(category);
            return category;
        } else {
            return entityManager.merge(category);
        }
    }

    @Override
    public Optional<Category> update(Category category) {
        Category updatedCategory = entityManager.merge(category);
        return Optional.ofNullable(updatedCategory);
    }

    @Override
    public Optional<Category> findById(long id) {
        Category category = entityManager.find(Category.class, id);
        return Optional.ofNullable(category);
    }

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();
    }

    @Override
    public void delete(long id) {
        Category category = entityManager.find(Category.class, id);
        if (category != null) {
            entityManager.remove(category);
        }
    }
}
