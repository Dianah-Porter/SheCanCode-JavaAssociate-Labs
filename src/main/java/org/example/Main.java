package org.example;

import org.example.lab1_1.Product;
import org.example.lab1_1.WarehouseStore;

public class Main {
    public static void main(String[] args) {

        WarehouseStore<Product> store = new WarehouseStore<>();

        store.addItem(new Product("1", "Laptop", "Electronics", 1200));
        store.addItem(new Product("2", "Phone", "Electronics", 800));
        store.addItem(new Product("3", "Shoes", "Fashion", 100));

        System.out.println("All Electronics:");
        System.out.println(store.findByCategory("Electronics"));

        System.out.println("Remove product 2:");
        store.removeItem("2");

        System.out.println("All items:");
        System.out.println(store.getAll());

        //  THIS WILL NOT COMPILE (this is your proof)
        // WarehouseStore<String> badStore = new WarehouseStore<>();
    }
}