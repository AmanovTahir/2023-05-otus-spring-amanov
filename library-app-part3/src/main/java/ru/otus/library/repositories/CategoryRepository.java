package ru.otus.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
