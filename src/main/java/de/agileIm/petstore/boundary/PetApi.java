package de.agileIm.petstore.boundary;

import de.agileIm.petstore.entity.NewPet;
import de.agileIm.petstore.entity.Pet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PetApi {

    @Operation(
            operationId = "listPets",
            summary = "List all pets",
            tags = {"pets"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "A paged array of pets", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pet.class)))
                    }),
                    @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @GetMapping(value = "/pets", produces = "application/json")
    ResponseEntity<List<Pet>> listPets(
            @Parameter(name = "limit", description = "How many items to return at one time (max 100)", in = ParameterIn.QUERY) @Max(100) @Valid @RequestParam(value = "limit", required = false) Integer limit,
            @Parameter(name = "offset", description = "How many items to skip", in = ParameterIn.QUERY) @Valid @RequestParam(value = "offset", required = false) Integer offset
    );


    @Operation(
            operationId = "createPets",
            summary = "Create a pet",
            tags = {"pets"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "415", description = "Unsupported Media Type"),
                    @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @PostMapping(value = "/pets", produces = "application/json", consumes = "application/json")
    ResponseEntity<Pet> createPets(
            @Parameter(name = "NewPet", description = "Pet to add to the store", required = true) @Valid @RequestBody NewPet newPet
    );

    @Operation(
            operationId = "showPetById",
            summary = "Info for a specific pet",
            tags = {"pets"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Pet not found"),
                    @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @GetMapping(value = "/pets/{petId}", produces = "application/json")
    ResponseEntity<Pet> showPetById(
            @Parameter(name = "petId", description = "The id of the pet to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("petId") String petId
    );

    @Operation(
            operationId = "updatePetById",
            summary = "Update specific pet",
            tags = {"pets"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Expected response to a valid request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "404", description = "Pet not found"),
                    @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @PutMapping(value = "/pets/{petId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Pet> updatePetById(
            @Parameter(name = "petId", description = "The id of the pet to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("petId") String petId,
            @Parameter(name = "Pet", description = "Pet to update in the store", required = true) @Valid @RequestBody Pet pet);
}
