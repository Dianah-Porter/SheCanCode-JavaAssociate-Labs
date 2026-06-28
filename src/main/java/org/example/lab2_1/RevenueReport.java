package org.example.lab2_1;

public class RevenueReport {

    private double totalRevenue;
    private long itemCount;
    private double maxSingleItemRevenue;

    public RevenueReport() {
    }

    public RevenueReport(double totalRevenue, long itemCount, double maxSingleItemRevenue) {
        this.totalRevenue = totalRevenue;
        this.itemCount = itemCount;
        this.maxSingleItemRevenue = maxSingleItemRevenue;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    public double getMaxSingleItemRevenue() {
        return maxSingleItemRevenue;
    }

    public void setMaxSingleItemRevenue(double maxSingleItemRevenue) {
        this.maxSingleItemRevenue = maxSingleItemRevenue;
    }

    @Override
    public String toString() {
        return "RevenueReport{" +
                "totalRevenue=" + totalRevenue +
                ", itemCount=" + itemCount +
                ", maxSingleItemRevenue=" + maxSingleItemRevenue +
                '}';
    }
}