package de.agileIm.petstore.control.persistence;

import de.agileIm.petstore.entity.Pet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetStoreUnitTest {

    @Test
    void getAllPets() {

        final Pet pet = Pet.builder().id(1L).name("petname01").tag("pettag01").build();

        final PetStore petStore = new PetStore();
        petStore.addPet(pet);
        final List<Pet> allPets = petStore.getAllPets();


        assertEquals(allPets.size(), 1);
        assertEquals(allPets.contains(pet), true);


    }
}
