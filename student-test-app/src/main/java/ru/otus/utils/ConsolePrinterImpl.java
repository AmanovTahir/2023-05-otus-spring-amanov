package ru.otus.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsolePrinterImpl implements Printer {

    @Override
    public void print(String str) {
        System.out.println(str);
    }
}
