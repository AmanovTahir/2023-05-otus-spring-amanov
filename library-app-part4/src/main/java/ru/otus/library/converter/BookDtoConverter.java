package ru.otus.library.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.dto.BookDto;
import ru.otus.library.dto.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookDtoConverter implements Converter<BookDto, String> {

    @Override
    public String convert(BookDto source) {
        return String.format("id.%s — %s «%s» (%s)",
                source.getId(),
                getAuthors(source.getAuthors()),
                source.getTitle(),
                getCategories(source.getCategories()));
    }

    private String getAuthors(List<AuthorDto> authorList) {
        return authorList.stream()
                .map(author -> author.getFirstName() + " " + author.getLastName())
                .collect(Collectors.joining(", "));
    }

    private String getCategories(List<CategoryDto> categories) {
        return categories.stream()
                .map(CategoryDto::getName)
                .collect(Collectors.joining(", "));
    }
}
