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

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(PetServiceImpl.class)
public class PetServiceImplTest {


    @Configuration
    static class MyConfiguration {
        @Bean
        public PetDao petDaoInput() {
            PetDao mockPetDao = Mockito.mock(PetDao.class);
            when(mockPetDao.findAll()).thenReturn(List.of(new Pet("Maine Coon")));
            when(mockPetDao.findByBreed("Maine Coon")).thenReturn(Optional.of(new Pet("Maine Coon")));

            return mockPetDao;
        }

        @Bean
        PetService petService() {
            return new PetServiceImpl(petDaoInput());
        }
    }

    @Autowired
    private PetService petService;

    @Test
    void shouldHaveCorrectMethodFindByBreed() {

        Optional<Pet> actualPet = petService.findByBreed("Maine Coon");

        assertEquals("Maine Coon", actualPet.get().getBreed());

    }

    @Test
    void shouldHaveCorrectMethodFindAll() {

        assertEquals(1, petService.findAll().size());
    }

}
