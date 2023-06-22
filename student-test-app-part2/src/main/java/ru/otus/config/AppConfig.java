package ru.otus.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.DefaultResourceLoader;
import ru.otus.dao.Parser;
import ru.otus.dao.ParserCsv;
import ru.otus.dto.QuestionDto;
import ru.otus.service.CheckResultService;
import ru.otus.service.CheckResultServiceImpl;
import ru.otus.service.IOService;
import ru.otus.service.IOServiceStreams;

import java.util.Set;

@Data
@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Bean
    public CheckResultService checkResultService(@Value("${passingGrade}") int passingGrade) {
        return new CheckResultServiceImpl(passingGrade);
    }

    @Bean
    public IOService iOServiceStreams() {
        return new IOServiceStreams(System.out, System.in);
    }

    @Bean
    public Parser<QuestionDto> parserScv(@Value("${questions}") String path) {
        return new ParserCsv<>(new DefaultResourceLoader().getResource(path));
    }

    @Bean
    public ConversionServiceFactoryBean conversionService(Set<Converter<?, ?>> converters) {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.afterPropertiesSet();
        bean.setConverters(converters);
        return bean;
    }
}
