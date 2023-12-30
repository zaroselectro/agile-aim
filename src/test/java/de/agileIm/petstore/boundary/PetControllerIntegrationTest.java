package de.agileIm.petstore.boundary;

import de.agileIm.petstore.entity.Pet;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PetControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testHappyPath() throws Exception {

        // create pet 1
        final Pet newPet1 = Pet.builder().name("newPetName").tag("newPetTag").build();
        final MvcResult result1 = mvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPet1)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andReturn();
        final Pet pet1 = objectMapper.readValue(result1.getResponse().getContentAsString(), Pet.class);

        // create pet 2
        final Pet newPet2 = Pet.builder().name("newPetName").tag("newPetTag").build();
        final MvcResult result2 = mvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPet2)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andReturn();
        final Pet pet2 = objectMapper.readValue(result2.getResponse().getContentAsString(), Pet.class);


        // list all pets
        mvc.perform(get("/pets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[1].id").exists());


        // read pet1 by id
        mvc.perform(get("/pets/" + pet1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pet1.getId()))
                .andExpect(jsonPath("$.name").value(pet1.getName()))
                .andExpect(jsonPath("$.tag").value(pet1.getTag()));

        // update pet1 with new name
        pet1.setName("neuer Name");
        mvc.perform(put("/pets/" + pet1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pet1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("neuer Name"))
                .andReturn();


    }

}
