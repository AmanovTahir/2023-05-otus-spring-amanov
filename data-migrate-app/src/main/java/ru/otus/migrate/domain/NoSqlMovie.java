package ru.otus.migrate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "movies")
@Getter
@Setter
@NoArgsConstructor
public class NoSqlMovie {
    @Id
    private String id;

    private String title;

    private Integer releaseYear;

    @DBRef
    private List<NoSqlGenre> genres;

    @DBRef
    private List<NoSqlActor> actors;
}
