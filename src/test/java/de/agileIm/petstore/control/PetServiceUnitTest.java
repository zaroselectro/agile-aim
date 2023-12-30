package de.agileIm.petstore.control;

import de.agileIm.petstore.control.errors.UnexpectedErrorException;
import de.agileIm.petstore.control.persistence.PetPersistence;
import de.agileIm.petstore.entity.NewPet;
import de.agileIm.petstore.entity.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PetServiceUnitTest {

    private PetPersistence petPersistence = mock(PetPersistence.class);

    private PetService petService;

    @BeforeEach
    void setUp() {
        petService = new PetService(petPersistence);
    }

    @Test
    void getAllPets() {
        // when
        petService.getAllPets(1, 2);

        //then
        verify(petPersistence, times(1)).findAllPetsByRange(1, 2);
    }

    @Test
    void createPet() {
        // given
        final NewPet newPet = NewPet.builder().name("abc").tag("xyz").build();

        // when
        petService.createPet(newPet);

        //then
        verify(petPersistence, times(1)).saveOrUpdatePet(any(Pet.class));
    }

    @Test()
    void createPetWithKeinTierName() {
        // given
        final NewPet newPet = NewPet.builder().name("KeinTierName").tag("xyz").build();

        // when
        Assertions.assertThrows(UnexpectedErrorException.class, () -> {
            petService.createPet(newPet);
        });
    }

    @Test
    void showPetById() {

        // when
        petService.showPetById("123");

        //then
        verify(petPersistence, times(1)).findPetById(123L);
    }

    @Test
    void updatePetById() {
        //given
        final Pet pet = Pet.builder().id(123L).name("abc").tag("xyz").build();

        // when
        petService.updatePetById("123", pet);

        //then
        verify(petPersistence, times(1)).saveOrUpdatePet(pet);

    }
}
