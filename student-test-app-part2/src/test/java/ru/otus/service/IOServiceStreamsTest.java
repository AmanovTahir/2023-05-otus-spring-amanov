package ru.otus.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сервис вводы и вывода")
class IOServiceStreamsTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private IOService ioService;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("должен напечатать строку")
    void outputString() {
        ioService = new IOServiceStreams(System.out, System.in);
        ioService.outputString("simple text");
        assertEquals("simple text", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("должен считать число из потока")
    void readInt() {
        ioService = new IOServiceStreams(System.out, new ByteArrayInputStream("1".getBytes()));
        assertEquals(1, ioService.readInt());
    }

    @Test
    @DisplayName("должен считать число из потока вместе с выводом текста")
    void readIntWithPrompt() {
        ioService = new IOServiceStreams(System.out, new ByteArrayInputStream("1".getBytes()));
        assertEquals(1, ioService.readIntWithPrompt("simple text"));
        assertEquals("simple text", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("должен считать строку из потока вместе с выводом текста")
    void readStringWithPrompt() {
        ioService = new IOServiceStreams(System.out, new ByteArrayInputStream("1".getBytes()));
        assertEquals("1", ioService.readStringWithPrompt("simple text"));
        assertEquals("simple text", outputStreamCaptor.toString().trim());
    }

}