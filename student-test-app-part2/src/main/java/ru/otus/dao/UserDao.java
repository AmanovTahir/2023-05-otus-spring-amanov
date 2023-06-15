package ru.otus.dao;

import ru.otus.domain.User;

public interface UserDao {
    User get(String user);
}
