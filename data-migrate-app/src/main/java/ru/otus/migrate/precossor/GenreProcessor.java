package ru.otus.migrate.precossor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.migrate.domain.NoSqlGenre;
import ru.otus.migrate.domain.SqlGenre;
import ru.otus.migrate.mapper.GenreMapperImpl;

@Component
@RequiredArgsConstructor
public class GenreProcessor implements ItemProcessor<SqlGenre, NoSqlGenre> {

    private final GenreMapperImpl mapper;

    @Override
    public NoSqlGenre process(@NonNull SqlGenre entity) {
        return mapper.toNoSqlEntity(entity);
    }
}
