package org.example.lab3;


import org.example.lab3.MatchResult;
import org.example.lab3.Order;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class OrderBook {

    private final Queue<Order> buyOrders = new ConcurrentLinkedQueue<>();
    private final Queue<Order> sellOrders = new ConcurrentLinkedQueue<>();

    private final ReentrantLock lock = new ReentrantLock();

    public void addOrder(Order order) {
        if (order.getType() == Order.Type.BUY) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
    }

    public MatchResult matchOrders() {
        lock.lock();
        try {
            Order buy = buyOrders.peek();
            Order sell = sellOrders.peek();

            if (buy == null || sell == null) return null;

            int matchedQty = Math.min(buy.getQuantity(), sell.getQuantity());

            buyOrders.poll();
            sellOrders.poll();

            return new MatchResult(buy.getId(), sell.getId(), matchedQty);

        } finally {
            lock.unlock();
        }
    }

    public boolean hasOrders() {
        return !buyOrders.isEmpty() && !sellOrders.isEmpty();
    }
}
