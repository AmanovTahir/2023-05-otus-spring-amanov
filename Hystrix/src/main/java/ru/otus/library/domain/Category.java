package ru.otus.library.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document("categories")
@NoArgsConstructor
public class Category {
    @Id
    private String id;

    private String name;

    public Category(String name) {
        this.name = name;
    }
}
