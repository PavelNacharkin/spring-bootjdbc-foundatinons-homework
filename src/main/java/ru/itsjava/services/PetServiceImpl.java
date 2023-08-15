package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.PetDao;
import ru.itsjava.domain.Pet;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetDao petDao;

    @Override
    public List<Pet> findAll() {
        return petDao.findAll();
    }

    @Override
    public Optional<Pet> findByBreed(String breed) {
        return petDao.findByBreed(breed);
    }

}
