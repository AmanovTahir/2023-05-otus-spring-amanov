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
import ru.otus.migrate.domain.NoSqlMovie;
import ru.otus.migrate.domain.SqlMovie;
import ru.otus.migrate.precossor.MoviesProcessor;

@Configuration
public class MovieMigrationStep extends AbstractMigrationStep {

    private final MoviesProcessor moviesProcessor;

    public MovieMigrationStep(JobRepository jobRepository,
                              PlatformTransactionManager platformTransactionManager,
                              EntityManagerFactory factory,
                              MongoOperations mongoOperations,
                              MoviesProcessor moviesProcessor) {
        super(jobRepository, platformTransactionManager, factory, mongoOperations);
        this.moviesProcessor = moviesProcessor;
    }

    @Bean
    public JpaPagingItemReader<SqlMovie> movieReader() {
        return new JpaPagingItemReaderBuilder<SqlMovie>()
                .name("movieItemReader")
                .entityManagerFactory(factory)
                .queryString("SELECT m FROM SqlMovie m")
                .build();
    }

    @Bean
    public MongoItemWriter<NoSqlMovie> bookWriter() {
        return new MongoItemWriterBuilder<NoSqlMovie>()
                .collection("movies")
                .template(mongoOperations)
                .build();
    }

    @Bean
    public Step migrateMovieDataStep() {
        return new StepBuilder("migrateMovieDataStep", jobRepository)
                .<SqlMovie, NoSqlMovie>chunk(10, platformTransactionManager)
                .reader(movieReader())
                .processor(moviesProcessor)
                .writer(bookWriter())
                .build();
    }
}
