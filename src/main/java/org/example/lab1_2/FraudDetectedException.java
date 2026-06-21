package org.example.lab1_2;

public class FraudDetectedException extends TransactionException{
    public FraudDetectedException(String message, String errorCode){
        super(message, errorCode);
    }

}
