package com.degiro.trading.model;

import java.math.BigDecimal;

public class Result {

    private int flutOrderNumber;

    private BigDecimal accumulatedProfit;

    public int getFlutOrderNumber() {
        return flutOrderNumber;
    }

    public void setFlutOrderNumber(int flutOrderNumber) {
        this.flutOrderNumber = flutOrderNumber;
    }

    public BigDecimal getAccumulatedProfit() {
        return accumulatedProfit;
    }

    public void setAccumulatedProfit(BigDecimal accumulatedProfit) {
        this.accumulatedProfit = accumulatedProfit;
    }

   
}
