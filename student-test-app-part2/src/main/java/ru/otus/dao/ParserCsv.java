package ru.otus.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor
public class ParserCsv<T> implements Parser<T> {

    private final Resource resource;

    @Override
    public List<T> parse(Class<T> clazz) {
        if (Objects.requireNonNull(resource.getFilename()).isEmpty()) {
            return Collections.emptyList();
        }
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
        return mapper.schemaFor(clazz).withColumnSeparator(';')
                .withSkipFirstDataRow(true).withArrayElementSeparator("%");
    }
}
