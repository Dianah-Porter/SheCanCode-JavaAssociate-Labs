package org.example.lab1;

import java.util.Comparator;

public class ProductComparator {

    public static Comparator<Product> getComparator() {

        return Comparator
                .comparing(Product::getCategory)
                .thenComparing(Product::getPrice, Comparator.reverseOrder());
    }
}
