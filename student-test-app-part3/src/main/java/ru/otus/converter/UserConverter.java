package ru.otus.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.domain.User;
import ru.otus.exception.UsersNameException;

@Component
public class UserConverter implements Converter<String, User> {

    @Override
    public User convert(String user) {
        if (!user.isEmpty() && user.split("\\s+").length == 2) {
            String[] split = user.strip().split("\\s+");
            return User.builder().name(split[0]).surname(split[1]).build();
        }
        throw new UsersNameException("full name entered incorrectly");
    }
}
