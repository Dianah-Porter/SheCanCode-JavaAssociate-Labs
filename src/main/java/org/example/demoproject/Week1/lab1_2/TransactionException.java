package org.example.demoproject.Week1.lab1_2;

public class TransactionException extends Exception {

    private String errorCode;

    public TransactionException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}