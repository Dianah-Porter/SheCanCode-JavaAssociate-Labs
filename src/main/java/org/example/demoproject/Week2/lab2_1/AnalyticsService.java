package org.example.demoproject.Week2.lab2_1;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AnalyticsService {

    // Exercise 2.1

    public List<LineItem> flattenLineItems(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getLineItems().stream())
                .toList();
    }

    public double calculateHighQuantityRevenue(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getLineItems().stream())
                .filter(item -> item.getQuantity() > 5)
                .map(item -> item.getProduct().getPrice() * item.getQuantity())
                .reduce(0.0, Double::sum);
    }

    public List<ProductRevenue> topNProductsByRevenue(List<Order> orders, int n) {

        Map<Product, Double> revenueByProduct = orders.stream()
                .flatMap(order -> order.getLineItems().stream())
                .collect(Collectors.groupingBy(
                        LineItem::getProduct,
                        Collectors.summingDouble(item ->
                                item.getProduct().getPrice() * item.getQuantity())
                ));

        return revenueByProduct.entrySet()
                .stream()
                .map(entry -> new ProductRevenue(
                        entry.getKey().getProductId(),
                        entry.getKey().getProductName(),
                        entry.getValue()
                ))
                .sorted(Comparator.comparing(ProductRevenue::getRevenue).reversed())
                .limit(n)
                .toList();
    }

    // Exercise 2.2

    public Map<String, Long> countItemsByCategory(List<Order> orders) {

        return orders.stream()
                .flatMap(order -> order.getLineItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getProduct().getCategory(),
                        Collectors.counting()
                ));
    }

    public Map<Boolean, List<Order>> partitionOrders(List<Order> orders) {

        return orders.stream()
                .collect(Collectors.partitioningBy(Order::isDelivered));
    }

    public Map<String, Double> averagePriceByProduct(List<Order> orders) {

        return orders.stream()
                .flatMap(order -> order.getLineItems().stream())
                .collect(Collectors.toMap(
                        item -> item.getProduct().getProductId(),
                        item -> item.getProduct().getPrice(),
                        (price1, price2) -> (price1 + price2) / 2
                ));
    }

    // Exercise 2.3

    public RevenueReport generateRevenueReport(List<Order> orders) {

        return orders.stream()
                .flatMap(order -> order.getLineItems().stream())
                .collect(revenueCollector());
    }

    private Collector<LineItem, RevenueReport, RevenueReport> revenueCollector() {

        return Collector.of(

                RevenueReport::new,

                (report, item) -> {

                    double revenue =
                            item.getProduct().getPrice() * item.getQuantity();

                    report.setTotalRevenue(
                            report.getTotalRevenue() + revenue);

                    report.setItemCount(
                            report.getItemCount() + 1);

                    report.setMaxSingleItemRevenue(
                            Math.max(report.getMaxSingleItemRevenue(), revenue));

                },

                (r1, r2) -> {

                    r1.setTotalRevenue(
                            r1.getTotalRevenue() + r2.getTotalRevenue());

                    r1.setItemCount(
                            r1.getItemCount() + r2.getItemCount());

                    r1.setMaxSingleItemRevenue(
                            Math.max(r1.getMaxSingleItemRevenue(),
                                    r2.getMaxSingleItemRevenue()));

                    return r1;
                }

        );
    }

    public List<ProductRevenue> topNProductsByRevenueParallel(List<Order> orders, int n) {

        Map<Product, Double> revenueByProduct = orders.parallelStream()
                .flatMap(order -> order.getLineItems().stream())
                .collect(Collectors.groupingBy(
                        LineItem::getProduct,
                        Collectors.summingDouble(item ->
                                item.getProduct().getPrice() * item.getQuantity())
                ));

        return revenueByProduct.entrySet()
                .stream()
                .map(entry -> new ProductRevenue(
                        entry.getKey().getProductId(),
                        entry.getKey().getProductName(),
                        entry.getValue()
                ))
                .sorted(Comparator.comparing(ProductRevenue::getRevenue).reversed())
                .limit(n)
                .toList();
    }
}