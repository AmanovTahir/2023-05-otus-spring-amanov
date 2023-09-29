package ru.otus.migrate.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private final JobRepository jobRepository;

    @Bean
    public Job migrateDataJob(Step migrateMovieDataStep, Step migrateActorDataStep, Step migrateGenreDataStep) {
        return new JobBuilder("migrateMovieDataJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(splitFlow(migrateActorDataStep, migrateGenreDataStep))
                .next(migrateMovieDataStep)
                .build()
                .build();
    }

    @Bean
    public Flow splitFlow(Step migrateActorDataStep, Step migrateGenreDataStep) {
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(taskExecutor())
                .add(flow1(migrateActorDataStep), flow2(migrateGenreDataStep))
                .build();
    }

    @Bean
    public Flow flow1(Step migrateActorDataStep) {
        return new FlowBuilder<SimpleFlow>("flow1")
                .start(migrateActorDataStep)
                .build();
    }

    @Bean
    public Flow flow2(Step migrateGenreDataStep) {
        return new FlowBuilder<SimpleFlow>("flow2")
                .start(migrateGenreDataStep)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }
}
