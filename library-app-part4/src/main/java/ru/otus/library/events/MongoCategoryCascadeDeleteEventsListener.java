package ru.otus.library.events;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Category;
import ru.otus.library.repositories.BookRepository;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MongoCategoryCascadeDeleteEventsListener extends AbstractMongoEventListener<Category> {
    private final BookRepository bookRepository;

    @Override
    public void onAfterDelete(@NonNull AfterDeleteEvent<Category> event) {
        super.onAfterDelete(event);
        Document document = event.getSource();
        String id = document.get("_id").toString();
        bookRepository.removeCategoriesArrayElementsById(id);
        cleanNullCategoriesFromBooks(id);
    }

    private void cleanNullCategoriesFromBooks(String id) {
        List<Book> books = bookRepository.findByCategoriesId(id).stream().toList();
        for (Book book : books) {
            book.getCategories().removeIf(Objects::isNull);
            bookRepository.save(book);
        }
    }
}
