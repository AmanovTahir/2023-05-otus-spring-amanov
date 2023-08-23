package ru.otus.library.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Category;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.mapper.CategoryMapper;
import ru.otus.library.services.CategoryService;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryHandler {

    private final CategoryService service;

    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAll() {
        List<Category> categories = service.getAll();
        return categoryMapper.toDtoList(categories);
    }

    public CategoryDto insert(Category category) {
        return categoryMapper.toDto(service.insert(category));
    }

    public List<CategoryDto> getByIds(List<String> ids) {
        return ids.stream().map(service::getById).map(categoryMapper::toDto).toList();
    }
}
