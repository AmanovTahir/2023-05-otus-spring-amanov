package ru.otus.library.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Document("books")
public class Book implements Serializable {

    @Id
    private String id;

    private String title;

    @DBRef
    private List<Author> authors;

    @DBRef
    private List<Category> categories;

    public Book(String title, List<Author> authors, List<Category> categories) {
        this.title = title;
        this.authors = authors;
        this.categories = categories;
    }

    public Book() {
    }
}
