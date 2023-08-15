package ru.itsjava.services;

import ru.itsjava.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    List<Pet> findAll();
    Optional<Pet> findByBreed(String breed);
}
