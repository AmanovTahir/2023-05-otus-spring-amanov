package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.handler.AuthorHandler;

@ShellComponent
@AllArgsConstructor
public class AuthorController {

    private final AuthorHandler handler;

    @ShellMethod(value = "Delete author by ID", key = {"delete author", "d-a"})
    public String deleteById(long id) {
        return handler.deleteById(id);
    }

    @ShellMethod(value = "Get all authors", key = {"all authors", "a-a"})
    public String getAll() {
        return handler.getAllAuthorsString();
    }

    @ShellMethod(value = "Get a author by ID", key = {"get author", "g-a"})
    public String getById(long id) {
        return handler.getById(id);
    }

    @ShellMethod(value = "Update a author", key = {"update author", "u-a"})
    public String update(long id) {
        return handler.update(id);
    }

    @ShellMethod(value = "Insert a author", key = {"insert author", "i-a"})
    public String insert(String firstName, String lastName) {
        return handler.insert(firstName, lastName);
    }
}
