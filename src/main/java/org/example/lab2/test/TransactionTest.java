package org.example.lab2.test;

import org.example.lab2.model.Account;
import org.example.lab2.exception.TransactionException;

public class TransactionTest {

    public static void main(String[] args) {

        TransactionService service = new TransactionService();

        Account a1 = new Account("A1", 5000);
        Account a2 = new Account("A2", 1000);

        try {
            service.transfer(a1, a2, 2000);
            System.out.println("Transfer successful!");
        } catch (TransactionException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("A1 balance: " + a1.getBalance());
        System.out.println("A2 balance: " + a2.getBalance());
    }
}