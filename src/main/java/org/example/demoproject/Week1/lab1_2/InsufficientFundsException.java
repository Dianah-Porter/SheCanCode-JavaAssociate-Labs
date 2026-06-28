package org.example.demoproject.Week1.lab1_2;

public class InsufficientFundsException extends TransactionException{
    public InsufficientFundsException(String message, String errorCode) {
        super(message, errorCode);
    }
}
