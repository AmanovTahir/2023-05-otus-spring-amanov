package ru.otus.library.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping
public class BookPageController {

    @GetMapping
    public Mono<String> listBooksPage() {
        return Mono.just("list");
    }

    @GetMapping("/add")
    public Mono<String> addBookPage() {
        return Mono.just("add-book");
    }

    @GetMapping("/{id}")
    public Mono<String> detailsBookPage(@PathVariable String id, Model model) {
        model.addAttribute("bookId", id);
        return Mono.just("book-details");
    }

    @GetMapping("/edit/{id}")
    public Mono<String> editBookPage(@PathVariable String id, Model model) {
        model.addAttribute("bookId", id);
        return Mono.just("edit-book");
    }
}
