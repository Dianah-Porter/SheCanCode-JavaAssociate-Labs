package org.example.lab2.exception;

public class TransactionException extends Exception {

    private final String errorCode;

    public TransactionException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TransactionException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return "[" + errorCode + "] " + super.getMessage();
    }
}