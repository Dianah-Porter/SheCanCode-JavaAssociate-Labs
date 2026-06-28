package org.example.demoproject.Week2.lab2_1;

public class ProductRevenue {

    private final String productId;
    private final String productName;
    private final double revenue;

    public ProductRevenue(String productId, String productName, double revenue) {
        this.productId = productId;
        this.productName = productName;
        this.revenue = revenue;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getRevenue() {
        return revenue;
    }

    @Override
    public String toString() {
        return "ProductRevenue{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", revenue=" + revenue +
                '}';
    }
}