package ru.otus.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.List;


@Component
public class QuestionConverter implements Converter<Question, String> {

    @Override
    public String convert(Question question) {
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
