package ru.otus.library.domain;

import lombok.Data;

@Data
public class Category {
    private long id;

    private String name;

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }
}
