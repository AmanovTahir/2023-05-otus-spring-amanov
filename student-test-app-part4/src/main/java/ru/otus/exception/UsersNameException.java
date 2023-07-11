package ru.otus.exception;

public class UsersNameException extends IllegalArgumentException {
    public UsersNameException(String s) {
        super(s);
    }
}
