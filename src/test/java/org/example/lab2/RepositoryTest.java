package org.example.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class RepositoryTest {

    @Test
    void testExceptionChaining() {

        AccountRepository repo = new AccountRepository();

        DataAccessException ex = assertThrows(
                DataAccessException.class,
                () -> repo.getBalance("ACC001")
        );

        assertNotNull(ex.getCause());

        assertTrue(ex.getCause() instanceof SQLException);

        assertEquals("Database connection lost", ex.getCause().getMessage());
    }
}