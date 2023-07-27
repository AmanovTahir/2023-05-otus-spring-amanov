package ru.otus.library.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookDto;
import ru.otus.library.mapper.BookMapper;
import ru.otus.library.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookMapper.INSTANCE::bookToBookDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getById(long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return BookMapper.INSTANCE.bookToBookDto(book);
    }

    @Override
    @Transactional
    public BookDto update(Book book) {
        Book updateBook = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookDto(updateBook);
    }

    @Override
    @Transactional
    public BookDto insert(Book book) {
        Book updateBook = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookDto(updateBook);
    }
}
