package ru.itsjava.services;

import ru.itsjava.domain.Pet;

import java.util.List;

public interface PetService {
    List<Pet> findAll();
    Pet findByBreed(String breed);
}
