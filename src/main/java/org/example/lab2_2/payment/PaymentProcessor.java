package org.example.lab2_2.payment;

public class PaymentProcessor {

    private PaymentStrategy strategy;

    // set (change) strategy at runtime
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    // execute payment using current strategy
    public PaymentResult pay(PaymentRequest request) {
        if (strategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }

        return strategy.process(request);
    }
}