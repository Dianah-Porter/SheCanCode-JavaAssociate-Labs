package org.example.lab2_2.payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentProcessorTest {

    @Test
    void shouldProcessCreditCardPayment() {

        PaymentProcessor processor = new PaymentProcessor();
        processor.setStrategy(new CreditCardStrategy());

        PaymentRequest request = new PaymentRequest(100, "USD");

        PaymentResult result = processor.pay(request);

        assertTrue(result.isSuccess());
        assertEquals(3.0, result.getFee());
    }

    @Test
    void shouldProcessBankTransferPayment() {

        PaymentProcessor processor = new PaymentProcessor();
        processor.setStrategy(new BankTransferStrategy());

        PaymentRequest request = new PaymentRequest(100, "USD");

        PaymentResult result = processor.pay(request);

        assertTrue(result.isSuccess());
        assertEquals(2.0, result.getFee());
    }

    @Test
    void shouldProcessMobileMoneyPayment() {

        PaymentProcessor processor = new PaymentProcessor();
        processor.setStrategy(new MobileMoneyStrategy());

        PaymentRequest request = new PaymentRequest(100, "USD");

        PaymentResult result = processor.pay(request);

        assertTrue(result.isSuccess());
        assertEquals(1.5, result.getFee());
    }

    @Test
    void shouldThrowExceptionWhenNoStrategySet() {

        PaymentProcessor processor = new PaymentProcessor();

        PaymentRequest request = new PaymentRequest(100, "USD");

        assertThrows(IllegalStateException.class, () -> {
            processor.pay(request);
        });
    }
}