package de.agileIm.petstore.control.persistence;

import de.agileIm.petstore.entity.Pet;

import java.util.List;

public interface PetPersistence {

    Pet findPetById(Long id);

    List<Pet> findAllPetsByRange(Integer limit, Integer offset);

    Pet saveOrUpdatePet(Pet pet);

}
