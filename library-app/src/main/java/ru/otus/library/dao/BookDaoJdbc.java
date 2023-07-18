package ru.otus.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM books";
        return Objects.requireNonNull(jdbc.getJdbcOperations().queryForObject(sql, Integer.class));
    }

    @Override
    public Optional<Book> insert(Book book) {
        String sql = """
                INSERT INTO books (author_id, category_id, title)
                VALUES (:author_id, :category_id, :title)""";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("author_id", book.getAuthor().getId())
                .addValue("category_id", book.getCategory().getId())
                .addValue("title", book.getTitle());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(sql, params, keyHolder, new String[]{"book_id"});
        Number key = Objects.requireNonNull(keyHolder.getKey()).longValue();
        book.setId(key.longValue());
        return Optional.of(book);
    }


    @Override
    public Optional<Book> update(Book book) {
        String sql = """
                UPDATE books SET book_id=:book_id,
                author_id=:author_id,
                category_id=:category_id, title=:title where book_id = :book_id""";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("book_id", book.getId())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("category_id", book.getCategory().getId())
                .addValue("title", book.getTitle());
        jdbc.update(sql, params);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> getById(long id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("book_id", id);
        String sql = """
                select b.book_id, b.title, a.author_id,
                a.first_name, a.last_name, c.category_id, c.name
                from books b
                join authors a on a.author_id = b.author_id
                join categories c on c.category_id = b.category_id
                where book_id = :book_id;""";
        return Optional.ofNullable(jdbc.queryForObject(sql, params, new BookMapper()));
    }

    @Override
    public List<Book> getAll() {
        String sql = """
                select b.book_id, b.title, a.author_id,
                a.first_name, a.last_name, c.category_id, c.name
                from books b
                join authors a on a.author_id = b.author_id
                join categories c on c.category_id = b.category_id;""";
        return jdbc.query(sql, new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        String sql = "delete from books where book_id = :book_id";
        SqlParameterSource params = new MapSqlParameterSource().addValue("book_id", id);
        jdbc.update(sql, params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long categoryId = rs.getLong("category_id");
            String name = rs.getString("name");
            Category category = new Category(categoryId, name);

            long authorId = rs.getLong("author_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Author author = new Author(authorId, firstName, lastName);

            long bookId = rs.getLong("book_id");
            String title = rs.getString("title");
            return new Book(bookId, title, author, category);
        }
    }
}
