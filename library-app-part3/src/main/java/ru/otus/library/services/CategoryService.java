package ru.otus.library.services;

import ru.otus.library.domain.Category;

import java.util.List;

public interface CategoryService {
    void deleteById(long id);

    List<Category> getAll();

    Category getById(long id);

    Category update(Category category);

    Category insert(Category category);

}
