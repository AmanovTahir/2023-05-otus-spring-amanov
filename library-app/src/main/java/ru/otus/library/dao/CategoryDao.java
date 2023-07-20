package ru.otus.library.dao;

import ru.otus.library.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {


    int count();

    Optional<Category> insert(Category category);

    Optional<Category> update(Category category);

    Optional<Category> getById(long id);

    List<Category> getAll();

    void deleteById(long id);
}
