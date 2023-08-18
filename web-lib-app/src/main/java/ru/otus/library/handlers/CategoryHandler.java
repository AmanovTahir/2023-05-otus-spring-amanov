package ru.otus.library.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Category;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.mapper.CategoryMapper;
import ru.otus.library.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryHandler {

    private final CategoryService service;

    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAll() {
        return service.getAll().stream().map(categoryMapper::categoryToCategoryDto).collect(Collectors.toList());
    }

    public CategoryDto insert(Category category) {
        return categoryMapper.categoryToCategoryDto(service.insert(category));
    }
}
