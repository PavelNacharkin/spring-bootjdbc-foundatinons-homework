package ru.itsjava.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@Import(AppServiceImpl.class)
@SpringBootTest
public class AppServiceImplTest {

    @Configuration
    static class MyConfiguration {
        @Bean
        public UserService userService() {
            UserServiceImpl mockUserService = Mockito.mock(UserServiceImpl.class);
            when(mockUserService.findAll()).thenReturn(List.of(new User("Ivan", 22, new Pet("Maine Coon"))));
            return mockUserService;
        }

        @Bean
        public PetService petService() {
            PetServiceImpl mockPetService = Mockito.mock(PetServiceImpl.class);
            when(mockPetService.findByBreed("Maine Coon")).thenReturn(new Pet("Maine Coon"));
            return mockPetService;
        }

        @Bean
        public IOService ioService() {
            IOServiceImpl mockIOService = Mockito.mock(IOServiceImpl.class);
            when(mockIOService.input()).thenReturn(String.valueOf(2));
            return mockIOService;
        }

        @Bean
        public AppService appService(UserService userService, PetService petService, IOService ioService) {
            return new AppServiceImpl(userService, petService, ioService);

        }
    }

    @Autowired
    private AppService appService;

    @Test
    void shouldHaveCorrectMethodPrintAllUsers() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        appService.printAllUsers();

        assertEquals("Список пользователей[User(id=0, name=Ivan, age=22, pet=Pet(id=0, breed=Maine Coon))]" + "\r\n", outputStream.toString());

    }

    @Test
    void shouldHaveCorrectMethodInsertUser() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        appService.insertUser();

        assertEquals("Введите Пользователя \r\n" +
                "Введите Имя\r\n" +
                "Введите возраст\r\n" +
                "Выберите животное\r\n" + "[]\r\n", outputStream.toString());
    }

}
