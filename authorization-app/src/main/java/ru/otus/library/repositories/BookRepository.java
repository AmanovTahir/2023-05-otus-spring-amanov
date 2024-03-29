package ru.otus.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
