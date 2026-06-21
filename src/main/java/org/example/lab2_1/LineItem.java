package org.example.lab2_1;


public class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    // Convenience methods for the stream operations
    public String getCategory() {
        return product.getCategory();
    }

    public String getProductId() {
        return product.getId();
    }

    public double getRevenue() {
        return product.getPrice() * quantity;
    }

    public void setProduct(Product product) { this.product = product; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "LineItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
