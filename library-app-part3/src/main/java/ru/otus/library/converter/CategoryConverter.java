package ru.otus.library.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Category;

@Component
public class CategoryConverter implements Converter<Category, String> {
    @Override
    public String convert(Category source) {
        return "id." + source.getId() + " — " + source.getName();
    }
}
