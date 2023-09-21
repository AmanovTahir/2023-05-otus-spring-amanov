package ru.otus.library.services;

import ru.otus.library.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Long id);

    Category update(Category category);

    Category insert(Category category);

}
