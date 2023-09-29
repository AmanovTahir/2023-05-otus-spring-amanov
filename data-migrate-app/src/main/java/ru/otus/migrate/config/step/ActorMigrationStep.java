package ru.otus.migrate.config.step;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.transaction.PlatformTransactionManager;
import ru.otus.migrate.domain.NoSqlActor;
import ru.otus.migrate.domain.SqlActor;
import ru.otus.migrate.precossor.ActorProcessor;

@Configuration
public class ActorMigrationStep extends AbstractMigrationStep {

    private final ActorProcessor actorProcessor;

    public ActorMigrationStep(JobRepository jobRepository,
                              PlatformTransactionManager platformTransactionManager,
                              EntityManagerFactory factory,
                              MongoOperations mongoOperations,
                              ActorProcessor actorProcessor) {
        super(jobRepository, platformTransactionManager, factory, mongoOperations);
        this.actorProcessor = actorProcessor;
    }

    @Bean
    public JpaPagingItemReader<SqlActor> actorReader() {
        return new JpaPagingItemReaderBuilder<SqlActor>()
                .name("actorItemReader")
                .entityManagerFactory(factory)
                .queryString("SELECT a FROM SqlActor a")
                .build();
    }

    @Bean
    public MongoItemWriter<NoSqlActor> actorWriter() {
        return new MongoItemWriterBuilder<NoSqlActor>()
                .collection("actors")
                .template(mongoOperations)
                .build();
    }

    @Bean
    public Step migrateActorDataStep() {
        return new StepBuilder("migrateActorDataStep", jobRepository)
                .<SqlActor, NoSqlActor>chunk(10, platformTransactionManager)
                .reader(actorReader())
                .processor(actorProcessor)
                .writer(actorWriter())
                .build();
    }
}
