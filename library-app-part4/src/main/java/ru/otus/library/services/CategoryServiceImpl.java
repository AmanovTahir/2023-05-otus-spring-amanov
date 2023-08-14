package ru.otus.library.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Category;
import ru.otus.library.repositories.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getById(String id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category insert(Category category) {
        return categoryRepository.save(category);
    }
}
