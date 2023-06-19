package ru.otus.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import ru.otus.dao.Parser;
import ru.otus.dao.ParserCsv;
import ru.otus.dto.QuestionDto;
import ru.otus.service.CheckResultService;
import ru.otus.service.CheckResultServiceImpl;
import ru.otus.service.IOService;
import ru.otus.service.IOServiceStreams;

@Data
@Import(ConversionServiceConfig.class)
@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Value("${questions}")
    private String path;

    @Value("${passingGrade}")
    private int passingGrade;

    @Bean
    public CheckResultService checkResultService() {
        return new CheckResultServiceImpl(passingGrade);
    }

    @Bean
    public IOService iOServiceStreams() {
        return new IOServiceStreams(System.out, System.in);
    }

    @Bean
    public Parser<QuestionDto> parserScv() {
        return new ParserCsv<>(new DefaultResourceLoader().getResource(path));
    }
}
