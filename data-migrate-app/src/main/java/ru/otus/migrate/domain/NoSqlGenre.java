package ru.otus.migrate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genres")
@Getter
@Setter
@NoArgsConstructor
public class NoSqlGenre {
    @Id
    private String id;

    private String name;
}
