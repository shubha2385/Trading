package com.degiro.trading.model;

import java.math.BigDecimal;

import com.degiro.trading.constants.Price;

public class Flut {

    private int orderNumber;

    private BigDecimal buyingPrice;

    public int getOrderNumber() {
	return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
	this.orderNumber = orderNumber;
    }

    public BigDecimal getBuyingPrice() {
	return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
	this.buyingPrice = buyingPrice;
    }
    
    public BigDecimal getProfit() {
        return Price.SELLING_PRICE.getValue().subtract(buyingPrice);
    }

}
