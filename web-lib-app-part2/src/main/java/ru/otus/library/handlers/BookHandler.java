package ru.otus.library.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookDto;
import ru.otus.library.dto.CommentDto;
import ru.otus.library.mapper.BookMapper;
import ru.otus.library.services.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookHandler {

    private final BookService bookService;

    private final CommentHandler commentHandler;

    private final BookMapper bookMapper;


    public BookDto addBook(BookDto dto) {
        Book book = bookMapper.toDomain(dto);
        return bookMapper.toDto(bookService.insert(book));
    }

    public BookDto updateBook(BookDto dto) {
        Book domain = bookMapper.toDomain(dto);
        return bookMapper.toDto(bookService.update(domain));
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

}
