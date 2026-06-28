package org.example.lab2_2.order;

public interface OrderObserver {

    void onEvent(Order order, OrderEvent event);
}