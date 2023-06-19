package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.User;
import ru.otus.exception.UsersNameException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(String user) {
        if (!user.isEmpty() && user.split("\\s+").length == 2) {
            String[] split = user.strip().split("\\s+");
            return User.builder().name(split[0]).surname(split[1]).build();
        }
        throw new UsersNameException("full name entered incorrectly");
    }
}
