package ru.otus.migrate.precossor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.migrate.domain.NoSqlActor;
import ru.otus.migrate.domain.SqlActor;
import ru.otus.migrate.mapper.ActorMapperImpl;

@Component
@RequiredArgsConstructor
public class ActorProcessor implements ItemProcessor<SqlActor, NoSqlActor> {

    private final ActorMapperImpl mapper;

    @Override
    public NoSqlActor process(@NonNull SqlActor entity) {
        return mapper.toNoSqlEntity(entity);
    }
}
