package ru.otus.library.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.services.BookService;
import ru.otus.library.services.IOService.IOService;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookHandler {

    private final BookService bookService;

    private final ConversionService conversionService;

    private final IOService ioService;

    private final AuthorHandler authorHandler;

    private final CategoryHandler categoryHandler;


    public String insert(String title) {
        Author author = authorHandler.getAuthor();
        Category category = categoryHandler.getCategory();
        Book book = new Book(title, author, category);
        bookService.insert(book);
        String convert = conversionService.convert(book, String.class);
        return String.format("Книга – %s добавлена!", convert);
    }

    public String deleteById(long id) {
        Book book = bookService.getById(id);
        String convert = conversionService.convert(book, String.class);
        bookService.deleteById(id);
        return String.format("Книга – %s удалена!", convert);
    }

    public String getAllBooksString() {
        AtomicInteger counter = new AtomicInteger(0);
        return bookService.getAll()
                .stream()
                .map(book -> conversionService.convert(book, String.class))
                .map(booksString -> counter.incrementAndGet() + ") " + booksString)
                .collect(Collectors.joining("\n"));
    }

    public String getById(long id) {
        Book book = bookService.getById(id);
        return conversionService.convert(book, String.class);
    }

    public String update(long id) {
        Book book = bookService.getById(id);
        book.setTitle(ioService.readStringWithPrompt("Введите новое название книги:"));
        book.setAuthor(authorHandler.getAuthor());
        book.setCategory(categoryHandler.getCategory());
        Book update = bookService.update(book);
        return String.format("Книга – %s изменена!", conversionService.convert(update, String.class));
    }
}
