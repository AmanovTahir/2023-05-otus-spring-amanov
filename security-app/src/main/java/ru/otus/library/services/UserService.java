package ru.otus.library.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.library.domain.User;

public interface UserService extends UserDetailsService {

    User findByLogin(String username);
}
