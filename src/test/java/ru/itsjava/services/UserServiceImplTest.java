package ru.itsjava.services;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itsjava.dao.UserDao;
import ru.itsjava.dao.UserDaoImpl;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(UserServiceImpl.class)
public class UserServiceImplTest {

    @Configuration
    static class MyConfiguration {
        @Bean
        public UserDao userDao() {
            UserDaoImpl mockUserService = Mockito.mock(UserDaoImpl.class);
            when(mockUserService.findAll()).thenReturn(List.of(new User("Ivan", 22, new Pet("Maine Coon"))));

            return mockUserService;
        }

        @Bean
        UserService userService() {
            return new UserServiceImpl(userDao());
        }
    }

    @Autowired
    private UserService userService;


    @Test
    public void shouldHaveCorrectMethodInsert() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        User user = new User("Ivan", 22, new Pet("Maine Coon"));
        userService.insert(user);

        assertEquals("ID new User" + user.getId() + "\r\n", outputStream.toString());
    }

    @Test
    public void shouldHaveCorrectMethodFindAll() {

        assertEquals(1, userService.findAll().size());
    }
}
