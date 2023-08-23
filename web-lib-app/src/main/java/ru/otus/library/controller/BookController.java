package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.dto.BookDto;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.handlers.AuthorHandler;
import ru.otus.library.handlers.BookHandler;
import ru.otus.library.handlers.CategoryHandler;

import java.util.List;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
@Slf4j
public class BookController {

    private final BookHandler bookHandler;

    private final AuthorHandler authorHandler;

    private final CategoryHandler categoryHandler;

    @GetMapping("")
    public String lists(Model model) {
        List<BookDto> all = bookHandler.getAllBooks();
        model.addAttribute("books", all);
        return "book/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") String id) {
        bookHandler.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BookDto bookDto,
                         @RequestParam(name = "authorsIds", required = false) List<String> authorsIds,
                         @RequestParam(name = "categoriesIds", required = false) List<String> categoriesIds) {
        fillBookDto(bookDto, authorsIds, categoriesIds);
        bookHandler.updateBook(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") String id, Model model) {
        BookDto bookDto = bookHandler.getBook(id);
        List<AuthorDto> allAuthors = authorHandler.getAll();
        List<CategoryDto> allCategories = categoryHandler.getAll();

        model.addAttribute("book", bookDto);
        model.addAttribute("authors", allAuthors);
        model.addAttribute("categories", allCategories);

        return "book/edit";
    }

    @GetMapping("/add")
    public String add(Model model) {
        BookDto bookDto = new BookDto();
        List<AuthorDto> allAuthors = authorHandler.getAll();
        List<CategoryDto> allCategories = categoryHandler.getAll();

        model.addAttribute("bookDto", bookDto);
        model.addAttribute("authors", allAuthors);
        model.addAttribute("categories", allCategories);
        return "book/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute BookDto bookDto,
                      @RequestParam(name = "authorsIds", required = false) List<String> authorsIds,
                      @RequestParam(name = "categoriesIds", required = false) List<String> categoriesIds) {
        fillBookDto(bookDto, authorsIds, categoriesIds);
        bookHandler.addBook(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/book")
    public String view(@RequestParam("id") String id, Model model) {
        BookDto bookDto = bookHandler.getBook(id);
        model.addAttribute("book", bookDto);
        return "book/view";
    }

    private void fillBookDto(BookDto dto, List<String> authorsIds, List<String> categoriesIds) {
        List<CategoryDto> categoriesByIds = categoryHandler.getByIds(categoriesIds);
        List<AuthorDto> authorsByIds = authorHandler.getByIds(authorsIds);
        dto.setAuthors(authorsByIds);
        dto.setCategories(categoriesByIds);
    }
}
