package ru.otus.library.services;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Category;

import java.util.List;

public interface CategoryService {
    @Transactional
    void deleteById(long id);

    @Transactional(readOnly = true)
    List<Category> getAll();

    @Transactional(readOnly = true)
    Category getById(long id);

    @Transactional
    Category update(Category category);

    @Transactional
    Category insert(Category category);

    @Transactional(readOnly = true)
    int count();
}
