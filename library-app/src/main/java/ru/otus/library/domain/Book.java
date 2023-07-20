package ru.otus.library.domain;

import lombok.Data;

@Data
public class Book {

    private long id;

    private String title;

    private Author author;

    private Category category;

    public Book(String title, Author author, Category category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public Book(long id, String title, Author author, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
    }
}
