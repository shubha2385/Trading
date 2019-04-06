package com.degiro.trading.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OptimizationResult {

    private int pileNumber;

    private BigDecimal profit;

    private boolean isNumberOfFlutsUniquelyDetermined;

    private List<Integer> orderOfFlutsToBuy;

    public int getPileNumber() {
	return pileNumber;
    }

    public void setPileNumber(int pileNumber) {
	this.pileNumber = pileNumber;
    }

    public BigDecimal getProfit() {
	return profit;
    }

    public void setProfit(BigDecimal profit) {
	this.profit = profit;
    }

    public boolean isNumberOfFlutsUniquelyDetermined() {
	return isNumberOfFlutsUniquelyDetermined;
    }

    public void setNumberOfFlutsUniquelyDetermined(boolean isNumberOfFlutsUniquelyDetermined) {
	this.isNumberOfFlutsUniquelyDetermined = isNumberOfFlutsUniquelyDetermined;
    }

    public List<Integer> getOrderOfFlutsToBuy() {
	return orderOfFlutsToBuy;
    }

    public void setOrderOfFlutsToBuy(List<Integer> orderOfFlutsToBuy) {
	this.orderOfFlutsToBuy = orderOfFlutsToBuy;
    }

    public List<Integer> getOrderOfFlutsToBuy(int maxSize) {
	return orderOfFlutsToBuy.stream().limit(maxSize).collect(Collectors.toList());
    }

    public int getNumberOfFlutsToBuy() {
	return orderOfFlutsToBuy.size();
    }

    public boolean isPositiveProfit() {
	return getProfit().compareTo(BigDecimal.ZERO) > 0;
    }
}
