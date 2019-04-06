package com.degiro.trading.constants;

import java.math.BigDecimal;

public enum Price {

    SELLING_PRICE(BigDecimal.TEN);

    private final BigDecimal value;

    Price(BigDecimal value) {
	this.value = value;
    }
    
    public BigDecimal getValue() {
	return this.value;
    }

}
