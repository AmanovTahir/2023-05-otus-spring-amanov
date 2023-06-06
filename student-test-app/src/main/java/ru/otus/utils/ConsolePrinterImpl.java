package ru.otus.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsolePrinterImpl implements Printer {

    @Override
    public void print(Object o) {
        System.out.println(o.toString());
    }
}
