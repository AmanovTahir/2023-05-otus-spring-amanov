package ru.otus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.domain.Result;


@Service
@PropertySource("classpath:app.properties")
public class CheckResultServiceImpl implements CheckResultService {


    @Value("${passingGrade}")
    private int passingGrade;

    @Override
    public boolean check(Result result) {
        return passingGrade <= result.getPass();
    }
}
