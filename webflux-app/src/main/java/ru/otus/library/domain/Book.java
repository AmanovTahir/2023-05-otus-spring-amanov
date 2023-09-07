package ru.otus.library.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("books")
public class Book {

    @Id
    private String id;

    private String title;

    private List<Author> authors;

    private List<Category> categories;

    public Book(String title, List<Author> authors, List<Category> categories) {
        this.title = title;
        this.authors = authors;
        this.categories = categories;
    }
}
