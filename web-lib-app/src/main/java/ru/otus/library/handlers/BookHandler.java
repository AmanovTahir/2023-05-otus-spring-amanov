package ru.otus.library.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.dto.BookDto;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.dto.CommentDto;
import ru.otus.library.dto.RequestBookDto;
import ru.otus.library.mapper.AuthorMapper;
import ru.otus.library.mapper.BookMapper;
import ru.otus.library.mapper.CategoryMapper;
import ru.otus.library.services.BookService;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BookHandler {

    private final BookService bookService;

    private final AuthorHandler authorHandler;

    private final CategoryHandler categoryHandler;

    private final CommentHandler commentHandler;

    private final CategoryMapper categoryMapper;

    private final AuthorMapper authorMapper;

    private final BookMapper bookMapper;


    public void addBook(RequestBookDto dto) {
        fillBookDto(dto);

        Book book = bookMapper.toDomain(dto.getBookDto());
        bookService.insert(book);
    }

    public void updateBook(RequestBookDto dto) {
        fillBookDto(dto);

        Book book = bookMapper.toDomain(dto.getBookDto());
        book.setAuthors(getAuthors(book));
        book.setCategories(getCategory(book));

        bookService.update(book);
    }

    private void fillBookDto(RequestBookDto dto) {
        List<CategoryDto> categoriesByIds = categoryHandler.getByIds(dto.getCategoriesIds());
        List<AuthorDto> authorsByIds = authorHandler.getByIds(dto.getAuthorsIds());
        dto.getBookDto().setAuthors(authorsByIds);
        dto.getBookDto().setCategories(categoriesByIds);
    }

    public BookDto getBook(String id) {
        Book book = bookService.getById(id);
        List<CommentDto> comments = commentHandler.getCommentByBookId(id);

        BookDto bookDto = bookMapper.toDto(book);
        bookDto.setComments(comments);

        return bookDto;
    }

    public List<BookDto> getAllBooks() {
        return bookService.getAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public void deleteBook(String id) {
        bookService.deleteById(id);
    }

    private List<Author> getAuthors(Book book) {
        return book.getAuthors().stream().map(getAuthorFunction()).toList();
    }

    private List<Category> getCategory(Book book) {
        return book.getCategories().stream().map(getCategoryFunction()).toList();
    }

    private Function<Category, Category> getCategoryFunction() {
        return category -> {
            if (category.getId() == null) {
                return categoryMapper.toDomain(categoryHandler.insert(category));
            }
            return category;
        };
    }

    private Function<Author, Author> getAuthorFunction() {
        return author -> {
            if (author.getId() == null) {
                return authorMapper.toDomain(authorHandler.insert(author));
            }
            return author;
        };
    }
}
