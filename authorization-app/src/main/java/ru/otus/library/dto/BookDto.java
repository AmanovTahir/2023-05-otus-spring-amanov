package ru.otus.library.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {

    private Long id;

    private String title;

    private List<AuthorDto> authors;

    private List<CategoryDto> categories;

    private List<CommentDto> comments;

}
