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

    private final ConversionService conversionService;

    private final IOService ioService;

    private final AuthorService authorService;


    public String deleteById(long id) {
        Author author = authorService.getById(id);
        authorService.deleteById(id);
        String convert = conversionService.convert(author, String.class);
        return String.format("Автор – %s удален!", convert);
    }

    public String getById(long id) {
        Author author = authorService.getById(id);
        return conversionService.convert(author, String.class);
    }

    public String update(long id) {
        Author author = authorService.getById(id);
        author.setFirstName(ioService.readStringWithPrompt("Введите имя автора: "));
        author.setLastName(ioService.readStringWithPrompt("Введите фамилию автора: "));
        Author update = authorService.update(author);
        String convert = conversionService.convert(update, String.class);
        return String.format("Автор – %s изменен!", convert);
    }

    public Author getAuthor() {
        ioService.outputString(getAllAuthorsString());
        int choice = ioService.readIntWithPrompt("Выберите автора (0 для добавления нового): ");
        if (choice == 0) {
            Author author = Author.builder()
                    .firstName(ioService.readStringWithPrompt("Введите имя автора: "))
                    .lastName(ioService.readStringWithPrompt("Введите фамилию автора: "))
                    .build();
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
        Author author = Author.builder().firstName(firstName).lastName(lastName).build();
        authorService.insert(author);
        String convert = conversionService.convert(author, String.class);
        return String.format("Автор – %s добавлен!", convert);
    }
}
