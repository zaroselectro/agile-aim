package de.agileIm.petstore.control.persistence;

import de.agileIm.petstore.entity.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InMemoryPetPersistenceUnitTest {

    private IdGenerator idGenerator = mock(IdGenerator.class);
    private PetStore petStore = mock(PetStore.class);
    private InMemoryPetPersistence inMemoryPetPersistence;

    private Pet pet1;
    private Pet pet2;
    private Pet pet3;
    private Pet pet4;
    private Pet pet5;

    @BeforeEach
    void setUp() {
        initPets();

        inMemoryPetPersistence = new InMemoryPetPersistence(idGenerator, petStore);
    }


    @Test
    void findPetById() {
        // given
        when(petStore.getAllPets()).thenReturn(List.of(pet1, pet2, pet3, pet4, pet5));

        // when
        final Pet petById = inMemoryPetPersistence.findPetById(4L);

        // then
        assertEquals(petById.getId(), 4L);
        assertEquals(petById.getName(), "petname04");
        assertEquals(petById.getTag(), "pettag04");
    }

    @Test
    void saveOrUpdatePetCreateNewPet() {
        // given
        when(idGenerator.getNextId()).thenReturn(7L);
        final Pet newPet = Pet.builder().name("newPetName").tag("newPetTag").build();

        // when
        final Pet pet = inMemoryPetPersistence.saveOrUpdatePet(newPet);

        // then
        assertEquals(pet.getId(), 7L);
        assertEquals(pet.getName(), "newPetName");
        assertEquals(pet.getTag(), "newPetTag");
    }

    @Test
    void saveOrUpdatePetUpdateExistingPet() {
        // given
        when(petStore.getAllPets()).thenReturn(List.of(pet3));
        final Pet petToUpdate = Pet.builder().id(3L).name("updatedPetName").tag("updatedPetTag").build();

        // when
        final Pet updatedPet = inMemoryPetPersistence.saveOrUpdatePet(petToUpdate);

        // then
        assertEquals(updatedPet.getId(), 3L);
        assertEquals(updatedPet.getName(), "updatedPetName");
        assertEquals(updatedPet.getTag(), "updatedPetTag");
    }

    @Test
    void findAllPetsByRangeFindAll() {
        // given
        when(petStore.getAllPets()).thenReturn(List.of(pet1, pet2, pet3, pet4, pet5));

        // when
        final List<Pet> allPetsByRange = inMemoryPetPersistence.findAllPetsByRange(null, null);

        // then
        assertEquals(allPetsByRange.size(), 5);
    }

    @Test
    void findAllPetsByRangeFindSubset() {
        // given
        when(petStore.getAllPets()).thenReturn(List.of(pet1, pet2, pet3, pet4, pet5));

        // when
        final List<Pet> allPetsByRange = inMemoryPetPersistence.findAllPetsByRange(3, 2);

        // then
        assertEquals(allPetsByRange.size(), 3);
        assertEquals(allPetsByRange.contains(pet2), true);
        assertEquals(allPetsByRange.contains(pet3), true);
        assertEquals(allPetsByRange.contains(pet4), true);
    }


    private void initPets() {
        pet1 = Pet.builder().id(1L).name("petname01").tag("pettag01").build();
        pet2 = Pet.builder().id(2L).name("petname02").tag("pettag02").build();
        pet3 = Pet.builder().id(3L).name("petname03").tag("pettag03").build();
        pet4 = Pet.builder().id(4L).name("petname04").tag("pettag04").build();
        pet5 = Pet.builder().id(5L).name("petname05").tag("pettag05").build();
    }


}
