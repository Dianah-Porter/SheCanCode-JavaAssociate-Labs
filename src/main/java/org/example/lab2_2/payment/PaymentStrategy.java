package org.example.lab2_2.payment;

public interface PaymentStrategy {
    PaymentResult process(PaymentRequest request);
}