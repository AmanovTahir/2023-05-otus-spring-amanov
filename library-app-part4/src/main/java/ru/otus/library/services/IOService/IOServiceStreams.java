package ru.otus.library.services.IOService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Component
public class IOServiceStreams implements IOService {
    private final PrintStream output;

    private final Scanner input;

    public IOServiceStreams(
            @Value("#{T(System).out}") PrintStream outputStream,
            @Value("#{T(System).in}") InputStream inputStream) {
        output = outputStream;
        input = new Scanner(inputStream);
    }

    @Override
    public void outputString(String s) {
        output.println(s);
    }


    @Override
    public String readStringWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLine();
    }
}
