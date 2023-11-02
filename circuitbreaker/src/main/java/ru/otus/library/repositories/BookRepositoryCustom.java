package ru.otus.library.repositories;

public interface BookRepositoryCustom {
    void removeAuthorsArrayElementsById(String id);

    void removeCategoriesArrayElementsById(String id);
}
