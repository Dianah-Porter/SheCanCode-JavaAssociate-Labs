package org.example.demoproject.Week1.lab1_1;

import java.util.Comparator;

public class ProductComparator {

    public static Comparator<Product> getComparator() {

        return Comparator
                .comparing(Product::getCategory)
                .thenComparing(Product::getPrice, Comparator.reverseOrder());
    }
}
