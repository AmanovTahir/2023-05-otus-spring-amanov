package ru.otus.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"question", "answer1", "answer2", "answer3", "correct"})
public class Question {
    private String question;

    private String answer1;

    private String answer2;

    private String answer3;

    private String correct;
}
