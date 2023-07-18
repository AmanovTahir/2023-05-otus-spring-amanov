package ru.otus.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM authors";
        return Objects.requireNonNull(jdbc.getJdbcOperations().queryForObject(sql, Integer.class));
    }

    @Override
    public Optional<Author> insert(Author author) {
        String sql = "INSERT INTO authors (first_name, last_name) " +
                "VALUES (:first_name, :last_name)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("first_name", author.getFirstName())
                .addValue("last_name", author.getLastName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(sql, params, keyHolder, new String[]{"author_id"});
        Number key = Objects.requireNonNull(keyHolder.getKey()).longValue();
        author.setId(key.longValue());
        return Optional.of(author);
    }

    @Override
    public Optional<Author> update(Author author) {
        String sql = """
                UPDATE authors
                SET first_name=:first_name, last_name=:last_name
                WHERE author_id = :author_id""";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("author_id", author.getId())
                .addValue("first_name", author.getFirstName())
                .addValue("last_name", author.getLastName());
        jdbc.update(sql, params);
        return Optional.of(author);
    }

    @Override
    public Optional<Author> getById(long id) {
        try {
            String sql = """ 
                    SELECT author_id, first_name, last_name
                    FROM authors
                    WHERE author_id = :author_id""";
            SqlParameterSource params = new MapSqlParameterSource().addValue("author_id", id);
            return Optional.ofNullable(jdbc.queryForObject(sql, params, new AuthorMapper()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Author> getAll() {
        String sql = """
                SELECT author_id, first_name, last_name
                FROM authors""";
        return jdbc.query(sql, new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        String deleteAuthorSql =
                "DELETE FROM authors WHERE author_id = :author_id";
        SqlParameterSource paramsForAuthor = new MapSqlParameterSource()
                .addValue("author_id", id);
        jdbc.update(deleteAuthorSql, paramsForAuthor);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("author_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            return new Author(id, firstName, lastName);
        }
    }
}
