package com.tinubu.domain.usecase.policy;

import java.util.UUID;

public class PolicyIdGenerator {
    public static Long generate() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}