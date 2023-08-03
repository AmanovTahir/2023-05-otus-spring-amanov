package ru.otus.library.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.services.CommentService;
import ru.otus.library.services.IOService.IOService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentHandler {

    private final ConversionService conversionService;

    private final IOService ioService;

    private final CommentService service;

    private final BookHandler bookHandler;


    public String deleteById(String id) {
        Comment comment = service.getById(id);
        service.deleteById(id);
        String convert = conversionService.convert(comment, String.class);
        return String.format("Коментарий – %s удален!", convert);
    }

    public String getById(String id) {
        Comment comment = service.getById(id);
        return conversionService.convert(comment, String.class);
    }

    public String update(String id) {
        Comment comment = service.getById(id);
        comment.setText(ioService.readStringWithPrompt("Напишите коментарий: "));
        Comment update = service.update(comment);
        String convert = conversionService.convert(update, String.class);
        return String.format("Коментарий – %s изменен!", convert);
    }

    public String getAllCommentsByBookId(String bookId) {
        AtomicInteger counter = new AtomicInteger(0);
        List<Comment> allByBookId = service.getAllByBookId(bookId);
        return allByBookId
                .stream()
                .map(comment -> conversionService.convert(comment, String.class))
                .map(comment -> counter.incrementAndGet() + ") " + comment)
                .collect(Collectors.joining("\n"));
    }

    public String insert(String text) {
        Book book = bookHandler.getBook();
        Comment comment = new Comment(text, book);
        service.insert(comment);
        String convert = conversionService.convert(comment, String.class);
        return String.format("Коментарий – %s добавлен!", convert);
    }
}
