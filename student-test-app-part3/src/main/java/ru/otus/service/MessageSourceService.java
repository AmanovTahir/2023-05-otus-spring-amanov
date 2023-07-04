package ru.otus.service;

public interface MessageSourceService {
    String getMessage(String message, String[] args);

    String getMessage(String message);
}
