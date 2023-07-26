package ru.otus.library.services.IOService;


public interface IOService extends InputService, OutputService {

    String readStringWithPrompt(String prompt);
}
