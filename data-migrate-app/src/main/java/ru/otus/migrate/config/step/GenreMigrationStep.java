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
import ru.otus.migrate.domain.NoSqlGenre;
import ru.otus.migrate.domain.SqlGenre;
import ru.otus.migrate.precossor.GenreProcessor;

@Configuration
public class GenreMigrationStep extends AbstractMigrationStep {

    private final GenreProcessor genreProcessor;

    public GenreMigrationStep(JobRepository jobRepository,
                              PlatformTransactionManager platformTransactionManager,
                              EntityManagerFactory factory,
                              MongoOperations mongoOperations,
                              GenreProcessor genreProcessor) {
        super(jobRepository, platformTransactionManager, factory, mongoOperations);
        this.genreProcessor = genreProcessor;
    }

    @Bean
    public JpaPagingItemReader<SqlGenre> genreReader() {
        return new JpaPagingItemReaderBuilder<SqlGenre>()
                .name("genreItemReader")
                .entityManagerFactory(factory)
                .queryString("SELECT g FROM SqlGenre g")
                .build();
    }

    @Bean
    public MongoItemWriter<NoSqlGenre> genreWriter() {
        return new MongoItemWriterBuilder<NoSqlGenre>()
                .collection("genres")
                .template(mongoOperations)
                .build();
    }

    @Bean
    public Step migrateGenreDataStep() {
        return new StepBuilder("migrateGenreDataStep", jobRepository)
                .<SqlGenre, NoSqlGenre>chunk(10, platformTransactionManager)
                .reader(genreReader())
                .processor(genreProcessor)
                .writer(genreWriter())
                .build();
    }
}
