package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(PetDaoImpl.class)
public class PetJdbcDaoImplTest {
    @Autowired
    private PetDao petDao;


    @Test
    public void shouldHaveCorrectFindByBreed() {
        Pet pet = petDao.findByBreed("Maine Coon");

        assertEquals(pet.getBreed(), "Maine Coon");

    }

    @Test
    public void shouldHaveCorrectFindAll() {
        List<Pet> petList = petDao.findAll();

        assertEquals(1, petList.size());
    }
}
