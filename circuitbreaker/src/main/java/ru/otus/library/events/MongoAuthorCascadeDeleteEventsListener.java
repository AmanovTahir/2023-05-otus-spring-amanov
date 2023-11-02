package ru.otus.library.events;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Author;
import ru.otus.library.repositories.BookRepository;

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
    }
}
