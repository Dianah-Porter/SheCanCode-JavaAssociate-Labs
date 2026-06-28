package org.example.demoproject.lab2_2.order;

public class EmailNotifier implements OrderObserver {

    @Override
    public void onEvent(Order order, OrderEvent event) {
        System.out.println("EMAIL: Order " + order.getId() + " -> " + event);
    }
}