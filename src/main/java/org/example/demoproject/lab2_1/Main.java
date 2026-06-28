package org.example.demoproject.lab2_1;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Product laptop = new Product("P1", "Laptop", "Electronics", 1000);
        Product mouse = new Product("P2", "Mouse", "Electronics", 20);
        Product keyboard = new Product("P3", "Keyboard", "Electronics", 40);
        Product chair = new Product("P4", "Chair", "Furniture", 120);

        LineItem i1 = new LineItem(laptop, 2);
        LineItem i2 = new LineItem(mouse, 10);
        LineItem i3 = new LineItem(keyboard, 7);
        LineItem i4 = new LineItem(chair, 6);

        Order o1 = new Order("O1", List.of(i1, i2), true);
        Order o2 = new Order("O2", List.of(i3, i4), false);

        List<Order> orders = List.of(o1, o2);

        AnalyticsService service = new AnalyticsService();

        System.out.println("=== FLATTEN ===");
        service.flattenLineItems(orders).forEach(System.out::println);

        System.out.println("\n=== HIGH QUANTITY REVENUE ===");
        System.out.println(service.calculateHighQuantityRevenue(orders));

        System.out.println("\n=== TOP PRODUCTS ===");
        service.topNProductsByRevenue(orders, 3).forEach(System.out::println);

        System.out.println("\n=== CATEGORY COUNT ===");
        Map<String, Long> cat = service.countItemsByCategory(orders);
        System.out.println(cat);

        System.out.println("\n=== PARTITION ORDERS ===");
        System.out.println(service.partitionOrders(orders));

        System.out.println("\n=== AVERAGE PRICE ===");
        System.out.println(service.averagePriceByProduct(orders));

        System.out.println("\n=== REPORT ===");
        System.out.println(service.generateRevenueReport(orders));

        System.out.println("\n=== PARALLEL TOP ===");
        service.topNProductsByRevenueParallel(orders, 3).forEach(System.out::println);
    }
}