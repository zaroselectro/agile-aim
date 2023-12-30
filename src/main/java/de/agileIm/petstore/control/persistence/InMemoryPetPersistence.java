package de.agileIm.petstore.control.persistence;

import de.agileIm.petstore.entity.Pet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InMemoryPetPersistence implements PetPersistence {

    private final IdGenerator idGenerator;
    private final PetStore petStore;

    public InMemoryPetPersistence(IdGenerator idGenerator, PetStore petStore) {
        this.idGenerator = idGenerator;
        this.petStore = petStore;
    }

    @Override
    public Pet findPetById(Long id) {
        return petStore.getAllPets().stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

    @Override
    public List<Pet> findAllPetsByRange(Integer limit, Integer offset) {
        final List<Pet> allPets = petStore.getAllPets();
        final int size = allPets.size();
        int fromIndex = 0;
        int toIndex = size;

        if (offset != null && offset > 0) {
            int realOffset = offset - 1;
            fromIndex = realOffset <= toIndex ? realOffset : fromIndex;
        }
        if (limit != null && limit > 0) {
            toIndex = fromIndex + limit < toIndex ? fromIndex + limit : toIndex;
        }

        return allPets.subList(fromIndex, toIndex);
    }

    @Override
    public Pet saveOrUpdatePet(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(idGenerator.getNextId());
            petStore.addPet(pet);
            return pet;
        }

        final Pet petById = findPetById(pet.getId());
        if (petById != null) {
            petById.setName(pet.getName());
            petById.setTag(pet.getTag());
        }

        return petById;
    }

}
