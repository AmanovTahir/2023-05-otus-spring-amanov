package ru.otus.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import ru.otus.dao.Parser;
import ru.otus.dao.ParserCsv;
import ru.otus.dto.QuestionDto;
import ru.otus.service.IOService;
import ru.otus.service.IOServiceStreams;

@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Value("${questions}")
    private String path;

    @Bean
    public IOService iOServiceStreams() {
        return new IOServiceStreams(System.out, System.in);
    }

    @Bean
    public Parser<QuestionDto> parserScv() {
        return new ParserCsv<>(new DefaultResourceLoader().getResource(path));
    }

}
