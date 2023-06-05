package ru.otus.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"question", "answers", "correct"})
public class QuestionDto {

    private String question;

    private List<String> answers;

    private int correct;
}
