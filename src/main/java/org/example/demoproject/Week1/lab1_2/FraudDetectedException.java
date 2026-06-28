package org.example.demoproject.Week1.lab1_2;

public class FraudDetectedException extends TransactionException{
    public FraudDetectedException(String message, String errorCode){
        super(message, errorCode);
    }

}
