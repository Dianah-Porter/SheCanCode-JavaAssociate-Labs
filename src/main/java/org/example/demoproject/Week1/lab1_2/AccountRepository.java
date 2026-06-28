package org.example.demoproject.Week1.lab1_2;

import java.sql.SQLException;

public class AccountRepository {

    public double getBalance(String accountId) throws DataAccessException {

        try {
            // simulate database failure
            throw new SQLException("Database connection lost");

        } catch (SQLException e) {
            throw new DataAccessException("Failed to read account balance", e);
        }
    }
}