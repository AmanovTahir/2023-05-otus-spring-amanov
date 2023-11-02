package ru.otus.library.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.domain.Comment;
import ru.otus.library.repositories.AuthorRepository;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.repositories.CategoryRepository;
import ru.otus.library.repositories.CommentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ChangeLog(order = "001")
public class LibraryChangelogTest {
    private static final String NAME = "tamanov";

    private final Map<Integer, Author> authors = new HashMap<>() {{
        put(1, new Author("Мария", "Иванова"));
        put(2, new Author("Алексей", "Петрович"));
        put(3, new Author("Елена", "Смирнова"));
        put(4, new Author("Александр", "Иванов"));
        put(5, new Author("Анна", "Карпова"));
    }};

    private final Map<Integer, Category> categoryMap = new HashMap<>() {{
        put(1, new Category("Научная фантастика"));
        put(2, new Category("Фэнтези"));
        put(3, new Category("Романтическая драма"));
        put(4, new Category("Хроники о войне"));
        put(5, new Category("Приключенческая фантазия для детей"));
    }};

    private final List<Book> bookList = new ArrayList<>();


    @ChangeSet(order = "000", id = "dropDB", author = NAME, runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "insertAuthors", author = NAME, runAlways = true)
    public void insertAuthors(AuthorRepository repository) {
        repository.saveAll(authors.values());
    }

    @ChangeSet(order = "002", id = "insertCategories", author = NAME, runAlways = true)
    public void insertCategories(CategoryRepository repository) {
        repository.saveAll(categoryMap.values());
    }

    @ChangeSet(order = "003", id = "insertBooks", author = NAME, runAlways = true)
    public void insertBooks(BookRepository repository) {
        List<Book> books = getBookList();
        bookList.addAll(repository.saveAll(books));
    }

    @ChangeSet(order = "004", id = "insertComments", author = NAME, runAlways = true)
    public void insertComments(CommentRepository repository) {
        List<Comment> comments = getCommentList(bookList);
        repository.saveAll(comments);
    }

    private List<Book> getBookList() {
        return Arrays.asList(
                new Book("Звездный Охотник", List.of(authors.get(1)), List.of(categoryMap.get(1))),
                new Book("Путь Меча", List.of(authors.get(2)), List.of(categoryMap.get(2))),
                new Book("Мгновения вечности", List.of(authors.get(3)), List.of(categoryMap.get(3))),
                new Book("Тень Империи", List.of(authors.get(4)), List.of(categoryMap.get(4))),
                new Book("Волшебные Тайны", List.of(authors.get(5)), List.of(categoryMap.get(5)))
        );
    }

    private List<Comment> getCommentList(List<Book> books) {
        return Arrays.asList(
                new Comment("Увлекательная научная фантастика, читал на одном дыхании!",
                        books.get(0)),
                new Comment("Персонажи такие живые, что кажется, будто сам в космосе путешествуешь!",
                        books.get(0)),
                new Comment("Очарован миром фэнтези от Алексея Петровича!",
                        books.get(1)),
                new Comment("Эпические сражения и герои, которые остаются в памяти навсегда!",
                        books.get(1)),
                new Comment("Какое трогательное произведение, просто слов нет!",
                        books.get(2)),
                new Comment("С трудом удержал слезы, читая это прекрасное произведение.",
                        books.get(2)),
                new Comment("Фантастическая военная история, которая завладела моим сердцем.",
                        books.get(3)),
                new Comment("Александр Иванов - мастер создания напряженных моментов и сюжетных поворотов!",
                        books.get(3)),
                new Comment("Моя малышка просто обожает эту книжку и просит читать её снова и снова!",
                        books.get(4)),
                new Comment("Книга, которая запоминается на всю жизнь!",
                        books.get(4))
        );
    }
}
