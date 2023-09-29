package ru.otus.library.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.dto.BookDto;
import ru.otus.library.dto.CommentDto;
import ru.otus.library.mapper.BookMapper;
import ru.otus.library.mapper.CommentMapper;
import ru.otus.library.services.AuthorService;
import ru.otus.library.services.BookService;
import ru.otus.library.services.CategoryService;
import ru.otus.library.services.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookHandler {

    private final BookService bookService;

    private final AuthorService authorService;

    private final CommentService commentService;

    private final CategoryService categoryService;

    private final BookMapper bookMapper;

    private final CommentMapper commentMapper;


    public BookDto addBook(BookDto dto) {
        Book book = bookMapper.toDomain(dto);
        updateAuthorsAndCategoriesForBook(book);
        return bookMapper.toDto(bookService.insert(book));
    }

    private void updateAuthorsAndCategoriesForBook(Book book) {
        List<Long> authorIds = book.getAuthors().stream().map(Author::getId).toList();
        List<Long> categoryIds = book.getCategories().stream().map(Category::getId).toList();
        book.setAuthors(authorIds.stream().map(authorService::getById).toList());
        book.setCategories(categoryIds.stream().map(categoryService::getById).toList());
    }

    public BookDto updateBook(BookDto dto) {
        Book book = bookMapper.toDomain(dto);
        updateAuthorsAndCategoriesForBook(book);
        return bookMapper.toDto(bookService.update(book));
    }

    public BookDto getBook(Long id) {
        Book book = bookService.getById(id);
        List<CommentDto> comments = commentMapper.toDtoList(commentService.getAllByBookId(id));

        BookDto bookDto = bookMapper.toDto(book);
        bookDto.setComments(comments);

        return bookDto;
    }

    public List<BookDto> getAllBooks() {
        return bookService.getAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public void deleteBook(Long id) {
        commentService.deleteByBookId(id);
        bookService.deleteById(id);
    }

}
