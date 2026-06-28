package org.example.demoproject.Week1.lab1_1;

import java.util.List;

public class WarehouseStoreTest {

    public static void main(String[] args) {

        testAddItem();
        testFindByCategory();
        testRemoveItem();
        testGenericConstraint();
    }

    // 1. Test adding items
    static void testAddItem() {
        WarehouseStore<Product> store = new WarehouseStore<>();

        store.addItem(new Product("1", "Laptop", "Electronics", 1200));
        store.addItem(new Product("2", "Phone", "Electronics", 800));

        System.out.println("TEST addItem:");
        System.out.println(store.getAll());
        System.out.println("-------------");
    }

    // 2. Test findByCategory
    static void testFindByCategory() {
        WarehouseStore<Product> store = new WarehouseStore<>();

        store.addItem(new Product("1", "Laptop", "Electronics", 1200));
        store.addItem(new Product("2", "Shoes", "Fashion", 100));
        store.addItem(new Product("3", "TV", "Electronics", 900));

        List<Product> electronics = store.findByCategory("Electronics");

        System.out.println("TEST findByCategory:");
        System.out.println(electronics);
        System.out.println("-------------");
    }

    // 3. Test removeItem
    static void testRemoveItem() {
        WarehouseStore<Product> store = new WarehouseStore<>();

        store.addItem(new Product("1", "Laptop", "Electronics", 1200));
        store.addItem(new Product("2", "Phone", "Electronics", 800));

        boolean removed = store.removeItem("2");

        System.out.println("TEST removeItem:");
        System.out.println("Removed? " + removed);
        System.out.println(store.getAll());
        System.out.println("-------------");
    }

    static void testGenericConstraint() {

        System.out.println("TEST generic constraint:");

        WarehouseStore<Product> store = new WarehouseStore<>();

        // THIS LINE WILL NOT COMPILE (this is the proof)
        // WarehouseStore<String> invalidStore = new WarehouseStore<>();

        System.out.println("If String is uncommented → compile error (as expected)");
        System.out.println("-------------");
    }
}