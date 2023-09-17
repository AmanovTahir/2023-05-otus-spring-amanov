package ru.otus.library.changelog;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import io.mongock.driver.mongodb.reactive.util.MongoSubscriberSync;
import io.mongock.driver.mongodb.reactive.util.SubscriberSync;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.domain.Comment;
import ru.otus.library.repositories.AuthorRepository;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.repositories.CategoryRepository;
import ru.otus.library.repositories.CommentRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ChangeUnit(id = "initDatabase", order = "001", author = "tamanov", runAlways = true)
@RequiredArgsConstructor
public class LibraryChangelog {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    private final CategoryRepository categoryRepository;

    private final MongoDatabase mongoDatabase;


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

    @Execution
    public void initDB() {
        rollbackExecution();
        insertAuthors(authorRepository)
                .thenMany(insertCategories(categoryRepository))
                .then(insertBooks(bookRepository).collectList())
                .flatMapMany(bookList -> insertComments(commentRepository, bookList))
                .subscribe();
    }

    @RollbackExecution
    public void rollbackExecution() {
        SubscriberSync<DeleteResult> subscriber = new MongoSubscriberSync<>();
        Flux.from(mongoDatabase.listCollectionNames())
                .flatMap(collectionName ->
                        Mono.from(mongoDatabase.getCollection(collectionName)
                                .deleteMany(new Document()))
                ).subscribe(subscriber);
        subscriber.await();
    }

    public Flux<Author> insertAuthors(AuthorRepository repository) {
        return repository.saveAll(authors.values());
    }

    public Flux<Category> insertCategories(CategoryRepository repository) {
        return repository.saveAll(categoryMap.values());
    }

    public Flux<Book> insertBooks(BookRepository repository) {
        return repository.saveAll(getBookList());
    }

    public Flux<Comment> insertComments(CommentRepository repository, List<Book> books) {
        return repository.saveAll(getCommentList(books));
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
