package ru.otus.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
public class CsvParser<T> implements Parser<T> {
    private final ClassPathResource resource;

    @Override
    public List<T> parse(Class<T> clazz) {
        try (MappingIterator<T> iterator = getIterator(clazz, new CsvMapper())) {
            return iterator.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private MappingIterator<T> getIterator(Class<T> clazz, CsvMapper mapper) throws IOException {
        return mapper.readerFor(clazz).with(getSchema(clazz, mapper)).readValues(resource.getInputStream());
    }

    private CsvSchema getSchema(Class<T> clazz, CsvMapper mapper) {
        return mapper.schemaFor(clazz).withColumnSeparator(';').withSkipFirstDataRow(true);
    }
}
