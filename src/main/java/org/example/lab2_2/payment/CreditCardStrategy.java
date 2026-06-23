package org.example.lab2_2.payment;

public class CreditCardStrategy implements PaymentStrategy {

    @Override
    public PaymentResult process(PaymentRequest request) {

        double fee = request.getAmount() * 0.03; // 3% fee

        return new PaymentResult(true, fee);
    }
}