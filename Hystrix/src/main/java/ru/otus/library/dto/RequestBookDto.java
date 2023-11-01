package ru.otus.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestBookDto {
    private BookDto bookDto;

    private List<String> authorsIds;

    private List<String> categoriesIds;
}
