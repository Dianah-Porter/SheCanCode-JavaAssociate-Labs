package org.example.demoproject.lab2_2.order;

public class InventoryUpdater implements OrderObserver {

    @Override
    public void onEvent(Order order, OrderEvent event) {
        System.out.println("INVENTORY: Updated for order " + order.getId() + " -> " + event);
    }
}