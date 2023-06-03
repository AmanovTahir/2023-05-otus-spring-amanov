package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.RunnerService;
import ru.otus.service.RunnerServiceImpl;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appConfig.xml");
        RunnerService runnerService = context.getBean(RunnerServiceImpl.class);
        runnerService.printAnswers();
    }
}