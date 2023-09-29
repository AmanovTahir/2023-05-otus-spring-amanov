package ru.otus.migrate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.migrate.domain.NoSqlActor;
import ru.otus.migrate.domain.NoSqlGenre;
import ru.otus.migrate.domain.NoSqlMovie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBatchTest
@ContextConfiguration(initializers = DBContainerInitializer.class)
class JobConfigTest {
    private static final String JOB_NAME = "migrateMovieDataJob";
    private static final String COMPLETED = "COMPLETED";
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    void testJob() throws Exception {
        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo(JOB_NAME);

        JobParameters parameters = new JobParametersBuilder().toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(parameters);

        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo(COMPLETED);
        assertEquals(9, mongoTemplate.count(new Query(), NoSqlActor.class));
        assertEquals(3, mongoTemplate.count(new Query(), NoSqlGenre.class));
        assertEquals(3, mongoTemplate.count(new Query(), NoSqlMovie.class));
    }

}
