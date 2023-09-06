package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.library.dto.CategoryDto;
import ru.otus.library.mapper.CategoryMapper;
import ru.otus.library.repositories.CategoryRepository;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryRepository repository;

    private final CategoryMapper mapper;

    @GetMapping
    public Flux<CategoryDto> getAllCategories() {
        log.debug("REST requesting all categories");
        return repository.findAll().map(mapper::toDto);
    }
}
