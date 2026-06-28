package org.example.demoproject.Week1.lab1_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvTransactionProcessor {

    public void processTransactions(String inputFilePath, String errorFilePath) {

        List<ParseError> errors = new ArrayList<>();
        int processed = 0;

        // try-with-resources (REQUIRED)
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // Skip empty lines safely
                if (line.trim().isEmpty()) {
                    errors.add(new ParseError(lineNumber, line, "Empty line"));
                    continue;
                }

                try {
                    processLine(line);
                    processed++;

                } catch (Exception e) {
                    errors.add(new ParseError(lineNumber, line, e.getMessage()));
                }
            }

        } catch (IOException e) {
            System.out.println("Fatal error reading file: " + e.getMessage());
            return;
        }

        // Summary report
        int failed = errors.size();
        System.out.println("Processed: " + processed + ", Failed: " + failed);

        // Write failed records
        writeErrors(errorFilePath, errors);
    }

    private void processLine(String line) {
        // Expected format: from,to,amount

        String[] parts = line.split(",");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Malformed CSV format");
        }

        String from = parts[0];
        String to = parts[1];

        double amount;

        try {
            amount = Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount format");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }

        // simulate processing
        System.out.println("Processed transaction: " + from + " -> " + to + " : " + amount);
    }

    private void writeErrors(String errorFilePath, List<ParseError> errors) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(errorFilePath))) {

            for (ParseError error : errors) {
                writer.write(error.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Failed to write error file: " + e.getMessage());
        }
    }
}