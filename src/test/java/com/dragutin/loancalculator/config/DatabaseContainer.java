package com.dragutin.loancalculator.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class DatabaseContainer extends PostgreSQLContainer<DatabaseContainer> {
    private static final String IMAGE_VERSION = "postgres:14.1";
    private static DatabaseContainer container;

    private DatabaseContainer() {
        super(IMAGE_VERSION);
    }

    public static DatabaseContainer getInstance() {
        if (container == null) {
            container = new DatabaseContainer().withDatabaseName("loan");
        }

        return container;
    }
}
