package ru.otus.utils;

import lombok.RequiredArgsConstructor;
import ru.otus.domain.Question;

@RequiredArgsConstructor
public class ConsolePrinter {

    public void printQuestion(Question question) {
        System.out.println(
                "\n" + question.getQuestion() +
                        "\n\ta) " + question.getAnswer1() +
                        "\n\tb) " + question.getAnswer2() +
                        "\n\tc) " + question.getAnswer3()
        );
    }
}
