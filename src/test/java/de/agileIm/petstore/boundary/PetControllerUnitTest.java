package de.agileIm.petstore.boundary;

import de.agileIm.petstore.control.PetService;
import de.agileIm.petstore.entity.NewPet;
import de.agileIm.petstore.entity.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PetControllerUnitTest {


    private PetService petService = mock(PetService.class);

    private PetController petController;

    @BeforeEach
    void setUp() {
        petController = new PetController(petService);
    }

    @Test
    void listPets() {
        // when
        petController.listPets(1, 2);

        //then
        verify(petService, times(1)).getAllPets(1, 2);

    }

    @Test
    void createPets() {
        // given
        final NewPet newPet = NewPet.builder().name("abc").tag("xyz").build();

        // when
        petController.createPets(newPet);

        // then
        verify(petService, times(1)).createPet(newPet);
    }

    @Test
    void showPetById() {
        // when
        petController.showPetById("123");

        //then
        verify(petService, times(1)).showPetById("123");
    }

    @Test
    void updatePetById() {
        //given
        final Pet pet = Pet.builder().id(123L).name("abc").tag("xyz").build();

        // when
        petController.updatePetById("123", pet);

        //then
        verify(petService, times(1)).updatePetById("123", pet);
    }
}
