package ru.otus.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.library.domain.Category;
import ru.otus.library.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    CategoryDto categoryToCategoryDto(Category category);


    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    Category categoryDtoToCategory(CategoryDto categoryDto);
}
