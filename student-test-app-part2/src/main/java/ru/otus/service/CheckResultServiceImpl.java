package ru.otus.service;

import lombok.RequiredArgsConstructor;
import ru.otus.domain.Result;


@RequiredArgsConstructor
public class CheckResultServiceImpl implements CheckResultService {

    private final int passingGrade;

    @Override
    public boolean check(Result result) {
        return passingGrade <= result.getPass();
    }
}
