package ru.otus.library.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Getter
@Setter
@ToString
@Document("authors")
@NoArgsConstructor
public class Author implements Serializable {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
