package ru.itsjava.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(IOServiceImpl.class)
public class IOServiceImplTest {
    @Configuration
    static class ConfigurationIOServiceImpl {
        private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Putin".getBytes());

        @Bean
        public IOService ioService() {
            return new IOServiceImpl(byteArrayInputStream);
        }
    }

    @Autowired
    private IOService ioService;


    @Test
    public void shouldHaveCorrectMethodInput() {
        assertEquals("Putin", ioService.input());
    }
}
