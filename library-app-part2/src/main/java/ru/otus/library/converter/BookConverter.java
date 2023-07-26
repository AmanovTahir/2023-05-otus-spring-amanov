package ru.otus.library.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter implements Converter<Book, String> {

    @Override
    public String convert(Book source) {
        return String.format("id.%d — %s «%s» (%s)",
                source.getId(),
                getAuthors(source.getAuthors()),
                source.getTitle(),
                getCategories(source.getCategories()));
    }

    private String getAuthors(List<Author> authorList) {
        return authorList.stream()
                .map(author -> author.getFirstName() + " " + author.getLastName())
                .collect(Collectors.joining(", "));
    }

    private String getCategories(List<Category> categories) {
        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.joining(", "));
    }
}
