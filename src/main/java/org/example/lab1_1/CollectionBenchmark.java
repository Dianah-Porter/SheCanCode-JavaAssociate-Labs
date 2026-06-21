package org.example.lab1_1;

import java.util.*;

public class CollectionBenchmark {

    private static final int SIZE = 1000;

    public static void main(String[] args) {

        List<Product> products = generateProducts();

        benchmarkArrayList(products);
        benchmarkLinkedList(products);
        benchmarkHashSet(products);
        benchmarkTreeSet(products);
    }

    // -------------------------
    // Data Generator
    // -------------------------
    static List<Product> generateProducts() {
        List<Product> list = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            list.add(new Product(
                    String.valueOf(i),
                    "Product " + i,
                    i % 2 == 0 ? "Electronics" : "Fashion",
                    Math.random() * 1000
            ));
        }

        return list;
    }

    // -------------------------
    // ArrayList
    // -------------------------
    static void benchmarkArrayList(List<Product> data) {

        List<Product> list = new ArrayList<>();

        long start = System.nanoTime();
        list.addAll(data);
        long end = System.nanoTime();

        System.out.println("ArrayList insertion: " + (end - start));

        start = System.nanoTime();
        list.contains(data.get(500));
        end = System.nanoTime();

        System.out.println("ArrayList lookup: " + (end - start));

        start = System.nanoTime();
        for (Product p : list) {}
        end = System.nanoTime();

        System.out.println("ArrayList iteration: " + (end - start));
        System.out.println("--------------------");
    }

    // -------------------------
    // LinkedList
    // -------------------------
    static void benchmarkLinkedList(List<Product> data) {

        List<Product> list = new LinkedList<>();

        long start = System.nanoTime();
        list.addAll(data);
        long end = System.nanoTime();

        System.out.println("LinkedList insertion: " + (end - start));

        start = System.nanoTime();
        list.contains(data.get(500));
        end = System.nanoTime();

        System.out.println("LinkedList lookup: " + (end - start));

        start = System.nanoTime();
        for (Product p : list) {}
        end = System.nanoTime();

        System.out.println("LinkedList iteration: " + (end - start));
        System.out.println("--------------------");
    }

    // -------------------------
    // HashSet
    // -------------------------
    static void benchmarkHashSet(List<Product> data) {

        Set<Product> set = new HashSet<>();

        long start = System.nanoTime();
        set.addAll(data);
        long end = System.nanoTime();

        System.out.println("HashSet insertion: " + (end - start));

        start = System.nanoTime();
        set.contains(data.get(500));
        end = System.nanoTime();

        System.out.println("HashSet lookup: " + (end - start));

        start = System.nanoTime();
        for (Product p : set) {}
        end = System.nanoTime();

        System.out.println("HashSet iteration: " + (end - start));
        System.out.println("--------------------");
    }

    // -------------------------
    // TreeSet
    // -------------------------
    static void benchmarkTreeSet(List<Product> data) {

        Set<Product> set = new TreeSet<>(Comparator.comparing(Product::getId));

        long start = System.nanoTime();
        set.addAll(data);
        long end = System.nanoTime();

        System.out.println("TreeSet insertion: " + (end - start));

        start = System.nanoTime();
        set.contains(data.get(500));
        end = System.nanoTime();

        System.out.println("TreeSet lookup: " + (end - start));

        start = System.nanoTime();
        for (Product p : set) {}
        end = System.nanoTime();

        System.out.println("TreeSet iteration: " + (end - start));
        System.out.println("--------------------");
    }
}