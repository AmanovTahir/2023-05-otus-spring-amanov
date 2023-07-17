package ru.otus.library.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Category;
import ru.otus.library.services.CategoryService;
import ru.otus.library.services.IOService.IOService;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryHandler {

    private final ConversionService conversionService;

    private final IOService ioService;

    private final CategoryService categoryService;


    public String deleteById(long id) {
        Category category = categoryService.getById(id);
        categoryService.deleteById(id);
        String convert = conversionService.convert(category, String.class);
        return String.format("Жанр – %s удален!", convert);
    }

    public String getAllCategoriesString() {
        AtomicInteger counter = new AtomicInteger(0);
        return categoryService.getAll()
                .stream()
                .map(category -> conversionService.convert(category, String.class))
                .map(category -> counter.incrementAndGet() + ") " + category)
                .collect(Collectors.joining("\n"));
    }

    public String getById(long id) {
        Category category = categoryService.getById(id);
        return conversionService.convert(category, String.class);
    }

    public String update(long id) {
        Category category = categoryService.getById(id);
        String newName = ioService.readStringWithPrompt("Введите новое название жанра:");
        category.setName(newName);
        Category update = categoryService.update(category);
        String convert = conversionService.convert(update, String.class);
        return String.format("Жанр – %s изменен!", convert);

    }

    public String insert(String name) {
        Category category = new Category(name);
        categoryService.insert(category);
        return conversionService.convert(category, String.class);
    }

    public Category getCategory() {
        ioService.outputString(getAllCategoriesString());
        int choice = ioService.readIntWithPrompt("Выберите жанр (0 для добавления нового): ");
        if (choice == 0) {
            Category category = new Category(ioService.readStringWithPrompt("Введите жанр: "));
            categoryService.insert(category);
            return category;
        }
        return categoryService.getById(choice - 1);
    }
}
