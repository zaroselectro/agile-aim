package de.agileIm.petstore.control.persistence;

import de.agileIm.petstore.entity.Pet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetStore {

    private List<Pet> pets = new ArrayList<>();

    public List<Pet> getAllPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }
}
