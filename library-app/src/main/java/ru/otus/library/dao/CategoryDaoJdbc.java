package ru.otus.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryDaoJdbc implements CategoryDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM categories";
        return Objects.requireNonNull(jdbc.getJdbcOperations().queryForObject(sql, Integer.class));
    }

    @Override
    public Optional<Category> insert(Category category) {
        String sql = "INSERT INTO categories (name) " +
                "VALUES (:name)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", category.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(sql, params, keyHolder, new String[]{"category_id"});
        Number key = Objects.requireNonNull(keyHolder.getKey()).longValue();
        category.setId(key.longValue());
        return Optional.of(category);
    }

    @Override
    public Optional<Category> update(Category category) {
        String sql = """
                UPDATE categories
                SET name=:name where category_id = :category_id""";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("category_id", category.getId())
                .addValue("name", category.getName());
        jdbc.update(sql, params);
        return Optional.of(category);
    }

    @Override
    public Optional<Category> getById(long id) {
        try {
            String sql = "SELECT category_id, name FROM categories WHERE category_id = :category_id";
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("category_id", id);
            return Optional.ofNullable(jdbc.queryForObject(sql, params, new CategoryMapper()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Category> getAll() {
        String sql = "SELECT category_id, name FROM categories";
        return jdbc.query(sql, new CategoryMapper());
    }

    @Override
    public void deleteById(long id) {
        String deleteCategorySql =
                "DELETE FROM categories WHERE category_id = :category_id";
        SqlParameterSource paramsForCategory = new MapSqlParameterSource()
                .addValue("category_id", id);
        jdbc.update(deleteCategorySql, paramsForCategory);
    }

    private static class CategoryMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("category_id");
            String name = rs.getString("name");
            return new Category(id, name);
        }
    }
}
