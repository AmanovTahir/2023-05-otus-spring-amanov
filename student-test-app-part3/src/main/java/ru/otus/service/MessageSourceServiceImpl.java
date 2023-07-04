package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSourceServiceImpl implements MessageSourceService {

    private final LocaleProvider provider;

    private final MessageSource messageSource;

    @Override
    public String getMessage(String message, String[] args) {
        return messageSource.getMessage(message, args, provider.getLocale());
    }

    @Override
    public String getMessage(String message) {
        return messageSource.getMessage(message, null, provider.getLocale());
    }
}
