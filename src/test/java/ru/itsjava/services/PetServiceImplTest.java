package ru.itsjava.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itsjava.dao.PetDao;
import ru.itsjava.dao.PetDaoImpl;
import ru.itsjava.domain.Pet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(PetServiceImpl.class)
public class PetServiceImplTest {


    @Configuration
    static class MyConfiguration {
        @Bean
        public PetDao petDao() {
            PetDaoImpl mockPetService = Mockito.mock(PetDaoImpl.class);
            when(mockPetService.findAll()).thenReturn(List.of(new Pet("Maine Coon")));
            when(mockPetService.findByBreed("Maine Coon")).thenReturn(new Pet("Maine Coon"));

            return mockPetService;
        }

        @Bean
        PetService petService() {
            return new PetServiceImpl(petDao());
        }
    }

    @Autowired
    private PetService petService;

    @Test
    void shouldHaveCorrectMethodFindByBreed() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Pet pet = new Pet("Maine Coon");
        petService.findByBreed("Maine Coon");

        assertEquals("Maine Coon", outputStream.toString());

    }

    @Test
    void shouldHaveCorrectMethodFindAll() {

        assertEquals(1, petService.findAll().size());
    }

}
