package ru.otus.migrate.mapper;

import org.bson.types.ObjectId;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

    private final ExecutionContext executionContext;

    @Autowired
    public IdGenerator(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    public String generateNoSqlId(Class<?> entityClass, Long sqlId) {
        String key = entityClass.getSimpleName() + sqlId;
        if (executionContext.get(key) == null) {
            String value = new ObjectId().toHexString();
            executionContext.putString(key, value);
            return value;
        }
        return executionContext.getString(key);
    }
}