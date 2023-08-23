package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.mapper.AuthorMapper;
import ru.otus.library.services.AuthorService;

@Controller
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;

    @PostMapping("/add")
    public String add(@ModelAttribute AuthorDto authorDto) {
        Author author = authorMapper.toDomain(authorDto);
        authorService.insert(author);
        return "redirect:/books";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("newAuthor", new AuthorDto());
        return "author/add";
    }
}
