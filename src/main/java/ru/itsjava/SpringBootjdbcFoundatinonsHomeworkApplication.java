package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itsjava.dao.UserDao;
import ru.itsjava.domain.User;

import java.sql.SQLException;

@SpringBootApplication
public class SpringBootjdbcFoundatinonsHomeworkApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(SpringBootjdbcFoundatinonsHomeworkApplication.class, args);


        UserDao userDao = context.getBean(UserDao.class);
        System.out.println("userDao.count() = " + userDao.count());

        User user = new User("Victor", 26);
        userDao.insert(user);
        System.out.println("userDao.count() = " + userDao.count());

        User updateUser = new User("Georg", 18);
        updateUser.setId(2L);
        userDao.updateUser(updateUser);

        userDao.delete(updateUser);
        System.out.println("userDao.count() = " + userDao.count());

        Console.main(args);
    }
}
