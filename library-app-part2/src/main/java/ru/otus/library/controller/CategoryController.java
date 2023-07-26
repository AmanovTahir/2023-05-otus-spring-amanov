package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.handler.CategoryHandler;

@ShellComponent
@AllArgsConstructor
public class CategoryController {
    private final CategoryHandler handler;

    @ShellMethod(value = "Delete category by ID", key = {"delete category", "d-c"})
    public String deleteById(long id) {
        return handler.deleteById(id);
    }

    @ShellMethod(value = "Get all categories", key = {"all categories", "a-c"})
    public String getAll() {
        return handler.getAllCategoriesString();
    }

    @ShellMethod(value = "Get a category by ID", key = {"get category", "g-c"})
    public String getById(long id) {
        return handler.getById(id);
    }

    @ShellMethod(value = "Update a category", key = {"update category", "u-c"})
    public String update(long id) {
        return handler.update(id);
    }

    @ShellMethod(value = "Insert a category", key = {"insert category", "i-c"})
    public String insert(String title) {
        return handler.insert(title);
    }
}
