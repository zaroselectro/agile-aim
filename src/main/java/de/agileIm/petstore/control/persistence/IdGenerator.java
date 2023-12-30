package de.agileIm.petstore.control.persistence;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class IdGenerator {

    private AtomicLong petIdGenerator = new AtomicLong(1L);

    public Long getNextId() {
        return petIdGenerator.getAndIncrement();
    }
}
