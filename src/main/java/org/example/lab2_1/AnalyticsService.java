package org.example.lab2_1;
import org.example.lab2_1.Order;
import org.example.lab2_1.LineItem;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyticsService {

    // Exercise 2.1: Stream Pipeline for Product Analytics
    public List<LineItem> getAllLineItems(List<Order> orders) {
        // flatMap: Flattens List<List<LineItem>> into List<LineItem>
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.toList());
    }

    /**
     * Calculate total revenue from orders with quantity > 5
     */
    public double getRevenueFromLargeOrders(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .filter(item -> item.getQuantity() > 5)
                .mapToDouble(LineItem::getRevenue)
                .sum();
    }

    /**
     * Find the top N products by total revenue
     */
    public List<Map.Entry<String, Double>> topNProductsByRevenue(List<Order> orders, int n) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        LineItem::getProductId,
                        Collectors.summingDouble(LineItem::getRevenue)
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    // Exercise 2.2: Collectors & Grouping

    /**
     * Group line items by category and count items per category
     */
    public Map<String, Long> getItemCountByCategory(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        LineItem::getCategory,
                        Collectors.counting()
                ));
    }

    /**
     * Split orders into delivered and pending
     */
    public Map<Boolean, List<Order>> partitionOrdersByDelivery(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.partitioningBy(Order::isDelivered));
    }

    /**
     * Build a map of productId to average price across all line items
     */
    public Map<String, Double> getProductAveragePrice(List<Order> orders) {
        // First, collect all product prices per productId
        Map<String, List<Double>> productPrices = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        LineItem::getProductId,
                        Collectors.mapping(
                                item -> item.getProduct().getPrice(),
                                Collectors.toList()
                        )
                ));

        // Then calculate average for each product
        return productPrices.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToDouble(Double::doubleValue)
                                .average()
                                .orElse(0.0)
                ));
    }

    // Exercise 2.3: Custom Collector & Parallel Streams

    /**
     * Get revenue report using custom collector
     */
    public RevenueReport getRevenueReport(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(new RevenueReportCollector());
    }

    /**
     * Top N products using parallel stream
     */
    public List<Map.Entry<String, Double>> topNProductsParallel(List<Order> orders, int n) {
        return orders.parallelStream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        LineItem::getProductId,
                        Collectors.summingDouble(LineItem::getRevenue)
                ))
                .entrySet()
                .parallelStream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
}

// Inner class for Revenue Report (we'll move this later)
class RevenueReport {
    private double totalRevenue;
    private long itemCount;
    private double maxSingleItemRevenue;

    public RevenueReport(double totalRevenue, long itemCount, double maxSingleItemRevenue) {
        this.totalRevenue = totalRevenue;
        this.itemCount = itemCount;
        this.maxSingleItemRevenue = maxSingleItemRevenue;
    }

    public double getTotalRevenue() { return totalRevenue; }
    public long getItemCount() { return itemCount; }
    public double getMaxSingleItemRevenue() { return maxSingleItemRevenue; }

    @Override
    public String toString() {
        return "RevenueReport{" +
                "totalRevenue=" + totalRevenue +
                ", itemCount=" + itemCount +
                ", maxSingleItemRevenue=" + maxSingleItemRevenue +
                '}';
    }
}