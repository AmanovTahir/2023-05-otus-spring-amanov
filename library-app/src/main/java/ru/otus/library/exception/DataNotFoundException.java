package ru.otus.library.exception;

import org.springframework.dao.DataAccessException;

public class DataNotFoundException extends DataAccessException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
