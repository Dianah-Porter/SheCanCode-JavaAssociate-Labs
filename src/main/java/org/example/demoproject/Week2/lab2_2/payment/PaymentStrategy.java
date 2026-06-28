package org.example.demoproject.Week2.lab2_2.payment;

public interface PaymentStrategy {
    PaymentResult process(PaymentRequest request);
}