package ru.otus.library.mapper;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Category;
import ru.otus.library.dto.CategoryDto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    public CategoryDto toDto(@NotNull Category entity) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(entity.getId());
        categoryDto.setName(entity.getName());
        return categoryDto;
    }

    public List<CategoryDto> toDtoList(@NotNull List<Category> entities) {
        List<CategoryDto> list = new ArrayList<>(entities.size());
        for (Category category : entities) {
            list.add(toDto(category));
        }
        return list;
    }

    public Category toDomain(@NotNull CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }

    public List<Category> toDomainList(List<CategoryDto> dtos) {
        List<Category> list = new ArrayList<>(dtos.size());
        for (CategoryDto dto : dtos) {
            list.add(toDomain(dto));
        }
        return list;
    }
}
