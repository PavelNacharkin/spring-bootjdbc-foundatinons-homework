package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {
    private final UserService userService;
    private final PetService petService;
    private final IOService ioService;


    @Override
    public void start() {
        System.out.println("Добро пожаловать!");
        while (true) {
            System.out.println("Введите номер меню");
            System.out.println("1-- напечатать всех пользователей, 2 -- добавить пользователя");
            int menuNum = ioService.inputInt();
            if (menuNum == 1) {
                printAllUsers();

            } else if (menuNum == 2) {
                insertUser();

            } else {
                System.exit(0);
            }
        }
    }

    @Override
    public void printAllUsers() {
        List<User> userList = userService.findAll();
        System.out.println("Список пользователей" + userList);
    }

    @Override
    public void insertUser() {
        System.out.println("Введите Пользователя ");

        System.out.println("Введите Имя");
        String userName = ioService.input();

        System.out.println("Введите возраст");
        int age = ioService.inputInt();

        System.out.println("Выберите животное");
        System.out.println(petService.findAll().toString());
        String breed = ioService.input();
        Optional<Pet> petBreed = petService.findByBreed(breed);

        if (petBreed.isPresent()) {
            User user = new User(userName, age, petBreed.get());
            userService.insert(user);
        } else {
            System.out.println("Животное с указанной породой не найдено. Повторите попытку.");
        }
    }
}