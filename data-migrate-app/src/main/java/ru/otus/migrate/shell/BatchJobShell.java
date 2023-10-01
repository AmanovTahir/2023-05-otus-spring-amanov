package ru.otus.migrate.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class BatchJobShell {

    private final JobLauncher jobLauncher;

    private final Job migrateDataJob;

    @ShellMethod(value = "Запустить задачу", key = "run")
    public String runJob() {
        try {
            JobExecution execution = jobLauncher.run(migrateDataJob, getJobParameters());
            return "Задача успешно запущена. Статус выполнения: " + execution.getStatus();
        } catch (Exception e) {
            return "Ошибка при запуске задачи: " + e.getMessage();
        }
    }

    private JobParameters getJobParameters() {
        return new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
    }
}
