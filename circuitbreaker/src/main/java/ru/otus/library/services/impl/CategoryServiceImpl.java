package ru.otus.library.services.impl;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Category;
import ru.otus.library.repositories.CategoryRepository;
import ru.otus.library.services.CategoryService;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    @CircuitBreaker(name = "categoryServiceCircuitBreaker")
    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "categoryServiceCircuitBreaker")
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "categoryServiceCircuitBreaker")
    public Category getById(String id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "categoryServiceCircuitBreaker")
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "categoryServiceCircuitBreaker")
    public Category insert(Category category) {
        return categoryRepository.save(category);
    }
}
