package ru.otus.library.events;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.repositories.BookRepository;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MongoAuthorCascadeDeleteEventsListener extends AbstractMongoEventListener<Author> {
    private final BookRepository bookRepository;

    @Override
    public void onAfterDelete(@NonNull AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);
        Document document = event.getSource();
        String id = document.get("_id").toString();
        bookRepository.removeAuthorsArrayElementsById(id);
        cleanNullAuthorsFromBooks(id);

    }

    private void cleanNullAuthorsFromBooks(String id) {
        List<Book> books = bookRepository.findByAuthorsId(id).stream().toList();
        for (Book book : books) {
            book.getAuthors().removeIf(Objects::isNull);
            bookRepository.save(book);
        }
    }
}
