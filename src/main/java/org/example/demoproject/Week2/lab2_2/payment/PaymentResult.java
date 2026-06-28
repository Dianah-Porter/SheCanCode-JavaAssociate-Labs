package org.example.demoproject.lab2_2.payment;

public class PaymentResult {

    private final boolean success;
    private final double fee;

    public PaymentResult(boolean success, double fee) {
        this.success = success;
        this.fee = fee;
    }

    public boolean isSuccess() {
        return success;
    }

    public double getFee() {
        return fee;
    }
}