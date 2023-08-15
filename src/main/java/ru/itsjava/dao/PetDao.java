package ru.itsjava.dao;

import ru.itsjava.domain.Pet;

import java.util.List;
import java.util.Optional;

public interface PetDao {

    List<Pet> findAll();

    Optional<Pet> findByBreed(String breed);

}
