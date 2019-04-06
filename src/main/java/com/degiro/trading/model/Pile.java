package com.degiro.trading.model;

import java.util.List;

public class Pile {

    private int orderNumber;

    private List<Flut> fluts;

    public int getOrderNumber() {
	return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
	this.orderNumber = orderNumber;
    }

    public List<Flut> getFluts() {
	return fluts;
    }

    public void setFluts(List<Flut> fluts) {
	this.fluts = fluts;
    }

}
