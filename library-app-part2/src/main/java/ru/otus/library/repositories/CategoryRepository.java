package ru.otus.library.repositories;

import ru.otus.library.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);

    Optional<Category> update(Category category);

    Optional<Category> findById(long id);

    List<Category> findAll();

    void delete(long id);
}
