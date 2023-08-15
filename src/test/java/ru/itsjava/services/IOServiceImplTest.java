package ru.itsjava.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IOServiceImplTest {
    @Configuration
    static class ConfigurationIOServiceImpl {
        @Bean
        IOService ioServiceInput() {
            IOService mockIOService = Mockito.mock(IOServiceImpl.class);
            when(mockIOService.input()).thenReturn("Putin");
            when(mockIOService.inputInt()).thenReturn(23);
            return mockIOService;
        }

    }

    @Autowired
    private IOService ioService;

    @Test
    public void shouldHaveCorrectMethodInput() {
        assertEquals("Putin", ioService.input());
    }

    @Test
    public void shouldHaveCorrectMethodInputInt() {
        assertEquals(23, ioService.inputInt());
    }
}

