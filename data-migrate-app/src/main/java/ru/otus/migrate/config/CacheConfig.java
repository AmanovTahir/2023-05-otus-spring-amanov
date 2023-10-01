package ru.otus.migrate.config;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public ExecutionContext executionContext() {
        return new ExecutionContext();
    }
}
