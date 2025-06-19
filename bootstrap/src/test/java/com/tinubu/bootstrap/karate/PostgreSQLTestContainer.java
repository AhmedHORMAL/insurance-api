package com.tinubu.bootstrap.karate;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class PostgreSQLTestContainer {

    static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine")
            .withDatabaseName("test_db")
            .withUsername("user")
            .withPassword("pass");

    static {
        postgres.start();
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }
}