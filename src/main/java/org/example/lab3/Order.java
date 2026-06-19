package org.example.lab3;

public class Order {
    public enum Type { BUY, SELL }

    private final String id;
    private final Type type;
    private final int quantity;
    private final double price;

    public Order(String id, Type type, int quantity, double price) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}