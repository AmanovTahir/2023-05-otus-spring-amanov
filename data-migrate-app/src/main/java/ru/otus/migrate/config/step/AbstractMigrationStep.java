package ru.otus.migrate.config.step;

import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AllArgsConstructor
public abstract class AbstractMigrationStep {
    protected final JobRepository jobRepository;

    protected final PlatformTransactionManager platformTransactionManager;

    protected final EntityManagerFactory factory;

    protected final MongoOperations mongoOperations;
}
