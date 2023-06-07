package ru.otus.converter;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.List;

public class QuestionConverter {

    public String convertToString(Question question) {
        return question.getQuestion() + anwersToString(question.getAnswers());
    }

    private String anwersToString(List<Answer> answers) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < answers.size(); i++) {
            builder.append("\n\t").append(i + 1).append(") ").append(answers.get(i).getAnswer());
        }
        return builder.toString();
    }
}
