package ru.otus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleProvider {
    private final Locale locale;

    public LocaleProvider(@Value("${locale}") Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}
