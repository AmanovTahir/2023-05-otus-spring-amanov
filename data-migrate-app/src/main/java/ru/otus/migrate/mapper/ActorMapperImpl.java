package ru.otus.migrate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.migrate.domain.NoSqlActor;
import ru.otus.migrate.domain.SqlActor;


@Component
@RequiredArgsConstructor
public class ActorMapperImpl {

    private final IdGenerator idGenerator;

    public NoSqlActor toNoSqlEntity(SqlActor entity) {
        NoSqlActor noSqlActor = new NoSqlActor();

        noSqlActor.setId(idGenerator.generateNoSqlId(entity.getClass(), entity.getId()));
        noSqlActor.setFirstName(entity.getFirstName());
        noSqlActor.setLastName(entity.getLastName());

        return noSqlActor;
    }
}
