package org.example.demoproject.Week2.lab2_2.order;

public class AuditLogger implements OrderObserver {

    @Override
    public void onEvent(Order order, OrderEvent event) {
        System.out.println("AUDIT LOG: " + event + " for order " + order.getId());
    }
}