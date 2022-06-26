package com.dragutin.loancalculator.integration;

import com.dragutin.loancalculator.config.DatabaseContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class AbstractIntegrationTest {

    @Container
    public DatabaseContainer postgresDB = DatabaseContainer.getInstance();
}
