package ru.otus.library.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;

    private String title;

    private List<AuthorDto> authors;

    private List<CategoryDto> categories;

    private List<CommentDto> comments;

    public BookDto(String title, List<AuthorDto> authors, List<CategoryDto> categories) {
        this.title = title;
        this.authors = authors;
        this.categories = categories;
    }

    public BookDto(String title) {
        this.title = title;
    }
}
