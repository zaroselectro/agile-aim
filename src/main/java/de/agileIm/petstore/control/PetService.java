package de.agileIm.petstore.control;

import de.agileIm.petstore.control.errors.UnexpectedErrorException;
import de.agileIm.petstore.control.persistence.PetPersistence;
import de.agileIm.petstore.entity.NewPet;
import de.agileIm.petstore.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {


    private final PetPersistence petPersistence;

    public PetService(PetPersistence petPersistence) {
        this.petPersistence = petPersistence;
    }


    public List<Pet> getAllPets(Integer limit, Integer offset) {
        return petPersistence.findAllPetsByRange(limit, offset);
    }


    public Pet createPet(NewPet newPet) {
        if (newPet.getName().equals("KeinTierName")) {
            throw new UnexpectedErrorException("Es dürfen keine Tiere mit dem Name 'KeinTierName' angelegt werden");
        }
        final Pet pet = Pet.builder().tag(newPet.getTag()).name(newPet.getName()).build();
        return petPersistence.saveOrUpdatePet(pet);
    }


    public Pet showPetById(String petId) {
        Long id = Long.parseLong(petId);
        return petPersistence.findPetById(id);

    }


    public Pet updatePetById(String petId, Pet pet) {
        Long id = Long.parseLong(petId);
        if (!pet.getId().equals(id)) {
            throw new UnexpectedErrorException("Die Id vom Pet muss mit mit der angegebenen PathId übereinstimmen");
        }
        pet.setId(id);
        return petPersistence.saveOrUpdatePet(pet);
    }


}
