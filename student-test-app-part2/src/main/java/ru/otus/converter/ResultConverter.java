package ru.otus.converter;

import org.springframework.stereotype.Component;
import ru.otus.domain.Result;

@Component
public class ResultConverter implements Converter<Result, String> {

    @Override
    public String convert(Result result) {
        return result.getPass() + "/" + result.getTotal();
    }
}
