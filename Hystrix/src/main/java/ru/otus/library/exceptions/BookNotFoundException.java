package ru.otus.library.exceptions;

import java.util.NoSuchElementException;

public class BookNotFoundException extends NoSuchElementException {
    public BookNotFoundException(String s) {
        super(s);
    }
}
