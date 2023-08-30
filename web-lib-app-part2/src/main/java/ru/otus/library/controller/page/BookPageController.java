package ru.otus.library.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookPageController {
    @GetMapping("/")
    public String listBooksPage() {
        return "list";
    }

    @GetMapping("/add")
    public String addBookPage() {
        return "add-book";
    }

    @GetMapping("/{id}")
    public String detailsBookPage(@PathVariable String id, Model model) {
        model.addAttribute("bookId", id);
        return "book-details";
    }

    @GetMapping("/edit/{id}")
    public String editBookPage(@PathVariable String id, Model model) {
        model.addAttribute("bookId", id);
        return "edit-book";
    }
}
