package org.example.lab1_1;

import java.util.ArrayList;
import java.util.List;

public class ComparatorTest {

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        products.add(new Product("1", "Laptop", "Electronics", 1200));
        products.add(new Product("2", "Phone", "Electronics", 800));
        products.add(new Product("3", "Shoes", "Fashion", 100));
        products.add(new Product("4", "Bag", "Fashion", 100));
        products.add(new Product("5", "TV", "Electronics", 1200));

        products.sort(ProductComparator.getComparator());

        System.out.println("Sorted Products:");
        for (Product p : products) {
            System.out.println(p);
        }
    }
}