package ru.otus.library.domain;


import lombok.Data;

@Data
public class Author {
    private long id;

    private String firstName;

    private String lastName;

    public Author(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
