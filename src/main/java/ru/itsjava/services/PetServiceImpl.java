package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.PetDao;
import ru.itsjava.domain.Pet;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetDao petDao;

    @Override
    public List<Pet> findAll() {
        return petDao.findAll();
    }

    @Override
    public Pet findByBreed(String breed) {
        return petDao.findByBreed(breed);
    }

    @Override
    public String toString() {
        return " " + petDao +" ";
    }
}
