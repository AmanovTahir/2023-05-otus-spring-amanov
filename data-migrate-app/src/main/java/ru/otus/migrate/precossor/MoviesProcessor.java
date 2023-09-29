package ru.otus.migrate.precossor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.migrate.domain.NoSqlMovie;
import ru.otus.migrate.domain.SqlMovie;
import ru.otus.migrate.mapper.MovieMapperImpl;

@Component
@RequiredArgsConstructor
public class MoviesProcessor implements ItemProcessor<SqlMovie, NoSqlMovie> {

    private final MovieMapperImpl mapper;

    @Override
    public NoSqlMovie process(@NonNull SqlMovie entity) {
        return mapper.toNoSqlEntity(entity);
    }
}
