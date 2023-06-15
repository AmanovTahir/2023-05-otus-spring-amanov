package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.User;
import ru.otus.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Сервис для получение юзера из данных")
@ExtendWith(MockitoExtension.class)
class UserDaoConsoleTest {

    @Mock
    private UserMapper mapper;
    @InjectMocks
    private UserDaoConsole userDao;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDao = new UserDaoConsole(mapper);
    }

    @Test
    @DisplayName("должен вернуть ожидаемого юзера из данных")
    void get() {
        String fullName = "John Smith";
        User expected = User.builder().name("John").surname("Smith").build();
        when(userDao.get(anyString())).thenReturn(expected);
        User actual = userDao.get(fullName);
        assertEquals(expected, actual);
    }
}