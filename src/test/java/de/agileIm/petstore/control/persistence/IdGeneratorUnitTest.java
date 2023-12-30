package de.agileIm.petstore.control.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdGeneratorUnitTest {

    @Test
    void getNextId() {

        final IdGenerator idGenerator = new IdGenerator();
        final Long id1 = idGenerator.getNextId();
        final Long id2 = idGenerator.getNextId();

        assertEquals(id1, 1L);
        assertEquals(id2, 2L);
    }
}
