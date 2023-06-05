package ru.otus.utils;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ConsolePrinterImpl implements Printer {

    @Override
    public void print(String question, List<String> answers) {
        System.out.println(question);
        for (int i = 0; i < answers.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + answers.get(i));
        }
    }
}
