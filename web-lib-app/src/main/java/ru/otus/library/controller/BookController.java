package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.dto.BookDto;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.handlers.BookHandler;
import ru.otus.library.mapper.BookMapper;

import java.util.List;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
@Slf4j
public class BookController {

    private final BookMapper bookMapper;

    private final BookHandler handler;

    @GetMapping
    public String getAllBooks(Model model) {
        List<BookDto> all = handler.getAllBooks();
        model.addAttribute("books", all);
        return "list";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") String id) {
        handler.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/update")
    public String updateBook(Book book) {
        handler.updateBook(bookMapper.bookToBookDto(book));
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String editPageById(@RequestParam("id") String id, Model model) {
        BookDto bookDto = handler.getBook(id);
        List<AuthorDto> allAuthors = handler.getAllAuthors();
        List<CategoryDto> allCategories = handler.getAllCategories();

        model.addAttribute("book", bookDto);
        model.addAttribute("authors", allAuthors);
        model.addAttribute("categories", allCategories);

        return "edit";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        List<AuthorDto> allAuthors = handler.getAllAuthors();
        List<CategoryDto> allCategories = handler.getAllCategories();

        model.addAttribute("book", new Book());
        model.addAttribute("authors", allAuthors);
        model.addAttribute("categories", allCategories);

        return "add_book";
    }

    @PostMapping("/add")
    public String addBook(Book book) {
        handler.addBook(bookMapper.bookToBookDto(book));
        return "redirect:/books";
    }

    @GetMapping("/book")
    public String viewPage(@RequestParam("id") String id, Model model) {
        BookDto bookDto = handler.getBook(id);
        model.addAttribute("book", bookDto);
        return "view";
    }
}
