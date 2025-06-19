package com.tinubu.domain.usecase.policy;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PolicyIdGeneratorTest {

    @Test
    void shouldGeneratePositiveLongId() {
        Long id = PolicyIdGenerator.generate();
        assertNotNull(id, "Generated ID should not be null");
        assertTrue(id > 0, "Generated ID should be a positive number");
    }

    @Test
    void shouldGenerateUniqueIds() {
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            Long id = PolicyIdGenerator.generate();
            assertTrue(ids.add(id), "Duplicate ID found: " + id);
        }
    }
}
