package ru.otus.migrate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.migrate.domain.NoSqlGenre;
import ru.otus.migrate.domain.SqlGenre;

@Component
@RequiredArgsConstructor
public class GenreMapperImpl {

    private final IdGenerator idGenerator;

    public NoSqlGenre toNoSqlEntity(SqlGenre entity) {
        NoSqlGenre noSqlGenre = new NoSqlGenre();

        noSqlGenre.setId(idGenerator.generateNoSqlId(entity.getClass(), entity.getId()));
        noSqlGenre.setName(entity.getName());

        return noSqlGenre;
    }
}
