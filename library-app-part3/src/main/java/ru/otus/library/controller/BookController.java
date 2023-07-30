package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.handler.BookHandler;

@ShellComponent
@AllArgsConstructor
public class BookController {

    private final BookHandler handler;

    @ShellMethod(value = "Insert a book", key = {"insert book", "i-b"})
    public String insert(String title) {
        return handler.insert(title);
    }

    @ShellMethod(value = "Get all books", key = {"get all book", "a-b"})
    public String getAll() {
        return handler.getAllBooksString();
    }

    @ShellMethod(value = "Get a book by ID", key = {"get book", "g-b"})
    public String getById(long id) {
        return handler.getById(id);
    }

    @ShellMethod(value = "Update a book", key = {"update book", "u-b"})
    public String update(long id) {
        return handler.update(id);
    }

    @ShellMethod(value = "Delete book by ID", key = {"delete book", "d-b"})
    public String deleteById(long id) {
        return handler.deleteById(id);
    }
}



