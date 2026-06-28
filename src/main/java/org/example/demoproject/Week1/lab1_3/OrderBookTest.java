package org.example.demoproject.Week1.lab1_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class OrderBookTest {

    public static void main(String[] args) throws InterruptedException {

        OrderBook orderBook = new OrderBook();
        ExecutorService executor = Executors.newFixedThreadPool(20);

        List<Callable<Void>> tasks = new ArrayList<>();

        // 10 BUY producers
        for (int i = 0; i < 10; i++) {
            int id = i;
            tasks.add(() -> {
                orderBook.addOrder(new Order("BUY-" + id,
                        Order.Type.BUY,
                        10,
                        100 + id));
                return null;
            });
        }

        // 10 SELL producers
        for (int i = 0; i < 10; i++) {
            int id = i;
            tasks.add(() -> {
                orderBook.addOrder(new Order("SELL-" + id,
                        Order.Type.SELL,
                        10,
                        100 + id));
                return null;
            });
        }

        executor.invokeAll(tasks);
        executor.shutdown();

        System.out.println("Orders submitted.");

        // Matching phase
        ExecutorService matcher = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            matcher.submit(() -> {
                var result = orderBook.matchOrders();
                if (result != null) {
                    System.out.println(result);
                }
            });
        }

        matcher.shutdown();
        matcher.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Matching complete.");
    }
}