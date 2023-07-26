package ru.otus.library.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Author;

@Component
public class AuthorConverter implements Converter<Author, String> {
    @Override
    public String convert(Author source) {
        return "id." + source.getId() + " — " + source.getFirstName() + " " + source.getLastName();
    }
}
