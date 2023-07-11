package ru.otus.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.domain.Result;

@Component
public class CheckResultServiceImpl implements CheckResultService {

    private final int passingGrade;

    public CheckResultServiceImpl(@Value("${passingGrade}") int passingGrade) {
        this.passingGrade = passingGrade;
    }

    @Override
    public boolean check(Result result) {
        return passingGrade <= result.getPass();
    }
}
