package ru.otus.library.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dao.CategoryDao;
import ru.otus.library.domain.Category;
import ru.otus.library.exception.DataNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    @Transactional
    public void deleteById(long id) {
        categoryDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getById(long id) {
        return categoryDao.getById(id).orElseThrow(() -> new DataNotFoundException("Жанр не найден!"));
    }

    @Override
    @Transactional
    public Category update(Category category) {
        return categoryDao.update(category).orElseThrow();
    }

    @Override
    @Transactional
    public Category insert(Category category) {
        return categoryDao.insert(category).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public int count() {
        return categoryDao.count();
    }
}
