package org.example.lab3;

import org.example.lab3.OrderBook;
import org.example.lab3.MatchResult;
import org.example.lab3.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class OrderEnginePerformance {

    public static void main(String[] args) throws Exception {

        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(processors);

        double totalThroughput = 0;

        for (int run = 0; run < 5; run++) {

            OrderBook orderBook = new OrderBook();

            long start = System.nanoTime();

            List<Future<MatchResult>> futures = new ArrayList<>();

            for (int i = 0; i < 100; i++) {

                int id = i;

                futures.add(pool.submit(() -> {
                    orderBook.addOrder(new Order("B-" + id, Order.Type.BUY, 5, 100));
                    orderBook.addOrder(new Order("S-" + id, Order.Type.SELL, 5, 100));

                    return orderBook.matchOrders();
                }));
            }

            int matched = 0;

            for (Future<MatchResult> f : futures) {
                try {
                    if (f.get() != null) matched++;
                } catch (ExecutionException e) {
                    System.err.println("Execution error: " + e.getMessage());
                }
            }

            long end = System.nanoTime();

            double seconds = (end - start) / 1_000_000_000.0;
            double throughput = matched / seconds;

            System.out.println("Run " + run + " throughput: " + throughput + " matches/sec");

            totalThroughput += throughput;
        }

        pool.shutdown();

        System.out.println("\nAVERAGE THROUGHPUT = " + (totalThroughput / 5));
    }
}