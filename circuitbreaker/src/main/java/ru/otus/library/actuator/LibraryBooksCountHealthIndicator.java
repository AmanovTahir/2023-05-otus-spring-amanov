package ru.otus.library.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.library.services.BookService;

@Component
@RequiredArgsConstructor
public class LibraryBooksCountHealthIndicator implements HealthIndicator {

    private final BookService bookService;

    @Override
    public Health health() {
        long bookCount = bookService.getBookCount();
        if (bookCount >= 10) {
            return Health.up()
                    .withDetail("message", "В библиотеке достаточно книг.")
                    .build();
        } else {
            return Health.down()
                    .withDetail("message", "В библиотеке меньше 10 книг, такое приложение никому не нужно.")
                    .build();
        }
    }
}
