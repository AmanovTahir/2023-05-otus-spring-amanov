package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.domain.User;
import ru.otus.mapper.UserMapper;

@Repository
@RequiredArgsConstructor
public class UserDaoConsole implements UserDao {

    private final UserMapper mapper;

    @Override
    public User get(String user) {
        return mapper.mapToUser(user);
    }
}
