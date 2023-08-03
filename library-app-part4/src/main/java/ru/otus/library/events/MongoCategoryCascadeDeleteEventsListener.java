package ru.otus.library.events;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Category;
import ru.otus.library.repositories.BookRepository;

@Component
@RequiredArgsConstructor
public class MongoCategoryCascadeDeleteEventsListener extends AbstractMongoEventListener<Category> {
    private final BookRepository bookRepository;

    @Override
    public void onAfterDelete(@NonNull AfterDeleteEvent<Category> category) {
        super.onAfterDelete(category);
        Document document = category.getSource();
        String id = document.get("_id").toString();
        bookRepository.removeAuthorsArrayElementsById(id);
    }
}
