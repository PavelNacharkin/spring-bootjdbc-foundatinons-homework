package ru.itsjava;


import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.itsjava.services.AppService;


import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJdbcFoundationsHomeworkApplication {

    public static void main(String[] args) throws SQLException {

        ApplicationContext context = SpringApplication.run(SpringBootJdbcFoundationsHomeworkApplication.class, args);
        context.getBean(AppService.class).start();

//        Console.main(args);
    }
}
