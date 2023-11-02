package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.handlers.CategoryHandler;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryHandler handler;

    @GetMapping("/")
    public List<CategoryDto> getAllCategories() {
        return handler.getAll();
    }
}
