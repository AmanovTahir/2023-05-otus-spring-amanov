package ru.otus.library.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Book;

@Component
public class BookConverter implements Converter<Book, String> {
    @Override
    public String convert(Book source) {
        return String.format("id.%d — %s %s «%s» (%s)",
                source.getId(),
                source.getAuthor().getFirstName(),
                source.getAuthor().getLastName(),
                source.getTitle(),
                source.getCategory().getName());
    }
}
