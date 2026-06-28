package org.example.demoproject.Week2.lab2_2.payment;

public class MobileMoneyStrategy implements PaymentStrategy {
    @Override
    public PaymentResult process(PaymentRequest request) {
        double fee = request.getAmount() * 0.015; // 1.5% fee
        return new PaymentResult(true, fee);
    }
}