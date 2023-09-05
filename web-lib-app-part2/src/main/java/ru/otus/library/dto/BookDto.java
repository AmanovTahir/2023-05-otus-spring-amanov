package ru.otus.library.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<AuthorDto> authors;

    @JsonProperty("categories")
    private List<CategoryDto> categories;

    @JsonProperty("comments")
    private List<CommentDto> comments;

    public BookDto(String id, String title, List<AuthorDto> authors, List<CategoryDto> categories) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.categories = categories;
    }
}
