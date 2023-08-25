package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.library.domain.Category;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.mapper.CategoryMapper;
import ru.otus.library.services.CategoryService;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;


    @PostMapping("/add")
    public String add(@ModelAttribute CategoryDto categoryDto) {
        Category category = categoryMapper.toDomain(categoryDto);
        categoryService.insert(category);
        return "redirect:/books";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("newCategory", new CategoryDto());
        return "category/add";
    }

}
