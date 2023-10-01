package ru.otus.migrate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@Testcontainers
public class DBContainerInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static final String IMAGE = "postgres:13.0-alpine";

    @Container
    private static final PostgreSQLContainer<?> sqlContainer =
            new PostgreSQLContainer<>(DockerImageName.parse(IMAGE).asCompatibleSubstituteFor("postgres"))
                    .withDatabaseName("test-db")
                    .withUsername("postgres")
                    .withPassword("postgres");

    static {
        sqlContainer.start();
    }

    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues.of(
                        "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
                        "spring.datasource.username=" + sqlContainer.getUsername(),
                        "spring.datasource.password=" + sqlContainer.getPassword())
                .applyTo(configurableApplicationContext.getEnvironment());
    }
}
