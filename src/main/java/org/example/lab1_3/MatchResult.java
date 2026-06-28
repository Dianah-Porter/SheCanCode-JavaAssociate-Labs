package org.example.lab1_3;

public class MatchResult {
    private final String buyOrderId;
    private final String sellOrderId;
    private final int matchedQuantity;

    public MatchResult(String buyOrderId, String sellOrderId, int matchedQuantity) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.matchedQuantity = matchedQuantity;
    }

    @Override
    public String toString() {
        return "MATCH -> BUY=" + buyOrderId +
                ", SELL=" + sellOrderId +
                ", QTY=" + matchedQuantity;
    }
}