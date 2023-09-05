package ru.otus.library.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import ru.otus.library.dto.BookDeserializer;
import ru.otus.library.dto.BookDto;

import java.time.Duration;

@Configuration
public class CacheConfiguration {

    /*
        RedisCacheManagerBuilderCustomizer функциональный интерфейс, который позволяет
        настраивать построитель RedisCacheManager.
        Этот построитель используется для создания и настройки менеджера кэша для Redis
     */
    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("bookCache",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(5))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair
                                        .fromSerializer(redisSerializer(objectMapper()))))
                .withCacheConfiguration("bookPostCache",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(5))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair
                                        .fromSerializer(new GenericJackson2JsonRedisSerializer())));
                /*
                    entryTtl(Duration.ofSeconds(5)) - метод устанавливает время
                    жизни элементов кэша. В данном случае, для "bookCache" установлено
                    время жизни 5 сек, что означает, что элементы будут автоматически
                    удалены из кэша через 5 сек. после добавления.
                 */
    }

    @Bean
    public RedisSerializer<Object> redisSerializer(ObjectMapper objectMapper) {
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule().addDeserializer(BookDto.class, new BookDeserializer()));
        return objectMapper;
    }
}
