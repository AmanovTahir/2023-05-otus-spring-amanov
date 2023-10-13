package ru.otus.dungeons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.dungeons.service.impl.RunnerService;

@SpringBootApplication
@Slf4j
public class DungeonsApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DungeonsApplication.class, args);
        RunnerService runnerService = ctx.getBean(RunnerService.class);
        runnerService.run();
    }
}
