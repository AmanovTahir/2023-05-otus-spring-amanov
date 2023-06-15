package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.UserDao;
import ru.otus.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Сервис пользователей")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userDao);
    }

    @DisplayName("должен вернуть ожидаемого пользователя")
    @Test
    void getUser() {
        String fullName = "John Smith";
        User expected = User.builder().name("John").surname("Smith").build();
        when(userDao.get(anyString())).thenReturn(expected);
        User actual = userService.getUser(fullName);
        assertEquals(expected, actual);
    }
}