package ru.otus.library.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.services.AuthorService;
import ru.otus.library.services.IOService.IOService;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorHandler {

    public static final String ENTER_AUTHOR_NAME = "Введите имя автора: ";

    public static final String ENTER_AUTHORS_LAST_NAME = "Введите фамилию автора: ";

    private final ConversionService conversionService;

    private final IOService ioService;

    private final AuthorService authorService;


    public String deleteById(String id) {
        Author author = authorService.getById(id);
        authorService.deleteById(id);
        String convert = conversionService.convert(author, String.class);
        return String.format("Автор – %s удален!", convert);
    }

    public String getById(String id) {
        Author author = authorService.getById(id);
        return conversionService.convert(author, String.class);
    }

    public String update(String id) {
        Author author = authorService.getById(id);
        author.setFirstName(ioService.readStringWithPrompt(ENTER_AUTHOR_NAME));
        author.setLastName(ioService.readStringWithPrompt(ENTER_AUTHORS_LAST_NAME));
        Author update = authorService.update(author);
        String convert = conversionService.convert(update, String.class);
        return String.format("Автор – %s изменен!", convert);
    }

    public Author getAuthor() {
        ioService.outputString(getAllAuthorsString());
        String choice = ioService.readStringWithPrompt("Выберите автора (0 для добавления нового): ");
        if (choice.equals("0")) {
            Author author = new Author(
                    ioService.readStringWithPrompt(ENTER_AUTHOR_NAME),
                    ioService.readStringWithPrompt(ENTER_AUTHORS_LAST_NAME));
            return authorService.insert(author);
        }
        return authorService.getById(choice);
    }

    public String getAllAuthorsString() {
        AtomicInteger counter = new AtomicInteger(0);
        return authorService.getAll()
                .stream()
                .map(author -> conversionService.convert(author, String.class))
                .map(author -> counter.incrementAndGet() + ") " + author)
                .collect(Collectors.joining("\n"));
    }

    public String insert(String firstName, String lastName) {
        Author author = new Author(firstName, lastName);
        authorService.insert(author);
        String convert = conversionService.convert(author, String.class);
        return String.format("Автор – %s добавлен!", convert);
    }
}
