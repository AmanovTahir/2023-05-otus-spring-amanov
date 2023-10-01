package ru.otus.migrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MigrateApplication {
    public static void main(String[] args) {
        SpringApplication.run(MigrateApplication.class, args);
    }
}
