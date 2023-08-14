package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.handler.CommentHandler;

@ShellComponent
@AllArgsConstructor
public class CommentController {

    private final CommentHandler handler;

    @ShellMethod(value = "Delete comment by ID", key = {"delete comment", "d-com"})
    public String deleteById(String id) {
        return handler.deleteById(id);
    }

    @ShellMethod(value = "Get all comments by book", key = {"all comments", "a-com"})
    public String getAllByBook(String bookId) {
        return handler.getAllCommentsByBookId(bookId);
    }

    @ShellMethod(value = "Get a comment by ID", key = {"get comments", "g-com"})
    public String getById(String id) {
        return handler.getById(id);
    }

    @ShellMethod(value = "Update a comment", key = {"update comment", "u-com"})
    public String update(String id) {
        return handler.update(id);
    }

    @ShellMethod(value = "Insert a comment", key = {"insert comment", "i-com"})
    public String insert(String title) {
        return handler.insert(title);
    }

}
