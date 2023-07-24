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
public class SpringBootJdbcFoundationsHomeworkApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(SpringBootJdbcFoundationsHomeworkApplication.class, args);

        Console.main(args);
    }
}
