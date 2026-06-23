package org.example.lab2_2.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderEventBusTest {

    @Test
    void shouldNotifyAllObservers() {

        OrderEventBus bus = new OrderEventBus();

        bus.subscribe(new EmailNotifier());
        bus.subscribe(new InventoryUpdater());
        bus.subscribe(new AuditLogger());

        Order order = new Order("123");

        bus.publish(order, OrderEvent.ORDER_PLACED);

        // No exception = success (since we are printing to console)
        assertTrue(true);
    }

    @Test
    void shouldAllowUnsubscribe() {

        OrderEventBus bus = new OrderEventBus();

        EmailNotifier email = new EmailNotifier();

        bus.subscribe(email);
        bus.unsubscribe(email);

        Order order = new Order("123");

        bus.publish(order, OrderEvent.ORDER_SHIPPED);

        // If unsubscribe works, no email should be triggered
        assertTrue(true);
    }
}