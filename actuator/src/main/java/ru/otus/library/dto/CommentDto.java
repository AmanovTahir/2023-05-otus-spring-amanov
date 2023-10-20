package ru.otus.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {
    private String id;

    private String text;

    private BookDto book;

    public CommentDto(String text, BookDto book) {
        this.text = text;
        this.book = book;
    }
}
