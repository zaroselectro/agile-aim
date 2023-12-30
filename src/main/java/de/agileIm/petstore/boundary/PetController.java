package de.agileIm.petstore.boundary;

import de.agileIm.petstore.control.PetService;
import de.agileIm.petstore.entity.NewPet;
import de.agileIm.petstore.entity.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetController implements PetApi {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Override
    public ResponseEntity<List<Pet>> listPets(Integer limit, Integer offset) {
        final List<Pet> pets = petService.getAllPets(limit, offset);

        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Pet> createPets(NewPet newPet) {
        final Pet pet = petService.createPet(newPet);

        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Pet> showPetById(String petId) {
        final Pet pet = petService.showPetById(petId);
        
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Pet> updatePetById(String petId, Pet pet) {
        final Pet updatedPet = petService.updatePetById(petId, pet);

        return new ResponseEntity<>(updatedPet, HttpStatus.OK);
    }
}
