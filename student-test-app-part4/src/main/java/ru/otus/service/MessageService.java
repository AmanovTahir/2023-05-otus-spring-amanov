package ru.otus.service;

public interface MessageService {
    String getMessage(String message, String... args);

    String getMessage(String message);
}
