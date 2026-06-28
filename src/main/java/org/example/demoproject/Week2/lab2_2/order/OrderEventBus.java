package org.example.demoproject.lab2_2.order;

import java.util.ArrayList;
import java.util.List;

public class OrderEventBus {

    private final List<OrderObserver> observers = new ArrayList<>();


    public void subscribe(OrderObserver observer) {
        observers.add(observer);
    }

    // unsubscribe (remove observer)
    public void unsubscribe(OrderObserver observer) {
        observers.remove(observer);
    }

    // publish event to all observers
    public void publish(Order order, OrderEvent event) {
        for (OrderObserver observer : observers) {
            observer.onEvent(order, event);
        }
    }
}