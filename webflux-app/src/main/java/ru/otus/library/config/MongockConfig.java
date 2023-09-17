package ru.otus.library.config;

import com.mongodb.reactivestreams.client.MongoClient;
import io.mongock.driver.mongodb.reactive.driver.MongoReactiveDriver;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import ru.otus.library.changelog.LibraryChangelog;

@ConditionalOnProperty(name = "mongock.enabled")
@Configuration
@EnableReactiveMongoRepositories(basePackages = "ru.otus.library.repositories")
public class MongockConfig {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    public MongoReactiveDriver mongoReactiveDriver(MongoClient reactiveMongoClient) {
        return MongoReactiveDriver.withDefaultLock(reactiveMongoClient, database);
    }

    @Bean
    public MongockInitializingBeanRunner mongockInitializingBeanRunner(MongoReactiveDriver driver,
                                                                       ApplicationContext context) {
        return MongockSpringboot.builder()
                .setDriver(driver)
                .addMigrationClass(LibraryChangelog.class)
                .setSpringContext(context)
                .setTransactionEnabled(false)
                .buildInitializingBeanRunner();
    }
}
