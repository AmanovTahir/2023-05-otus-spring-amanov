package ru.otus.library.mapper;

import org.mapstruct.Mapper;
import ru.otus.library.domain.Category;
import ru.otus.library.dto.CategoryDto;

@Mapper(config = MappingConfig.class)
public interface CategoryMapper extends MappingConfig<CategoryDto, Category> {

}
