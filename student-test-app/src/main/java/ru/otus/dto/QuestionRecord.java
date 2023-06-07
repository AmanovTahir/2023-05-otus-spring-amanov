package ru.otus.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data

@JsonPropertyOrder({"question", "correctNumber", "answers"})
public class QuestionRecord {
    private String question;

    private int correctNumber;

    private List<String> answers;
}
