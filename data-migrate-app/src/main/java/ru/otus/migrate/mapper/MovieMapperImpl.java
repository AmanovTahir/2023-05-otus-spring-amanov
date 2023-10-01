package ru.otus.migrate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.migrate.domain.NoSqlActor;
import ru.otus.migrate.domain.NoSqlGenre;
import ru.otus.migrate.domain.NoSqlMovie;
import ru.otus.migrate.domain.SqlActor;
import ru.otus.migrate.domain.SqlGenre;
import ru.otus.migrate.domain.SqlMovie;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieMapperImpl {

    private final ActorMapperImpl actorMapper;

    private final GenreMapperImpl genreMapper;

    private final IdGenerator idGenerator;


    public NoSqlMovie toNoSqlEntity(SqlMovie entity) {
        NoSqlMovie noSqlMovie = new NoSqlMovie();

        noSqlMovie.setId(idGenerator.generateNoSqlId(entity.getClass(), entity.getId()));
        noSqlMovie.setTitle(entity.getTitle());
        noSqlMovie.setReleaseYear(entity.getReleaseYear());
        noSqlMovie.setGenres(sqlGenreListToNoSqlGenreList(entity.getGenres()));
        noSqlMovie.setActors(sqlActorListToNoSqlActorList(entity.getActors()));

        return noSqlMovie;
    }

    protected List<NoSqlGenre> sqlGenreListToNoSqlGenreList(List<SqlGenre> list) {
        List<NoSqlGenre> resulList = new ArrayList<>(list.size());
        for (SqlGenre sqlGenre : list) {
            resulList.add(genreMapper.toNoSqlEntity(sqlGenre));
        }

        return resulList;
    }

    protected List<NoSqlActor> sqlActorListToNoSqlActorList(List<SqlActor> list) {
        List<NoSqlActor> resultList = new ArrayList<>(list.size());
        for (SqlActor sqlActor : list) {
            resultList.add(actorMapper.toNoSqlEntity(sqlActor));
        }

        return resultList;
    }
}
