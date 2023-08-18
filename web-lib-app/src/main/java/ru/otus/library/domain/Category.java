package ru.otus.library.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document("categories")
public class Category {
    @Id
    private String id;

    private String name;

    public Category(String name) {
        this.name = name;
    }
}
