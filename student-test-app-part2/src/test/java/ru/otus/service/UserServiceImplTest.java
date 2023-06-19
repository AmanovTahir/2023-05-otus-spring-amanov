package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сервис пользователей")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    private UserServiceImpl userService;


    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @DisplayName("должен вернуть ожидаемого пользователя")
    @Test
    void getUser() {
        String fullName = "John Smith";
        User expected = User.builder().name("John").surname("Smith").build();
        User actual = userService.getUser(fullName);
        assertEquals(expected, actual);
    }
}