package ru.otus.migrate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "actors")
@Getter
@Setter
@NoArgsConstructor
public class NoSqlActor {
    @Id
    private String id;

    private String firstName;

    private String lastName;
}
