package org.example.lab2_2.payment;

public class BankTransferStrategy implements PaymentStrategy {

    @Override
    public PaymentResult process(PaymentRequest request) {

        double fee = 2.0; // fixed fee

        return new PaymentResult(true, fee);
    }
}