package ru.otus.library.services;

import ru.otus.library.domain.Category;

import java.util.List;

public interface CategoryService {
    void deleteById(String id);

    List<Category> getAll();

    Category getById(String id);

    Category update(Category category);

    Category insert(Category category);

}
