package ru.otus.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document("comments")
public class Comment {
    @Id
    private String id;

    private String text;

    @DBRef
    private Book book;

    public Comment(String id, String text, Book book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }
}
