package com.degiro.trading.model;

import java.util.List;

public class TestCaseInput {

    private final List<List<Integer>> priceTable;

    public TestCaseInput(List<List<Integer>> priceTable) {
	this.priceTable = priceTable;
    }

    public List<List<Integer>> getPriceTable() {
	return priceTable;
    }
}
