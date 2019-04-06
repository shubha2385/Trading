package com.degiro.trading.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.degiro.trading.model.Flut;
import com.degiro.trading.model.Pile;

public final class PileFactory {

    private final AtomicInteger flutNumber = new AtomicInteger();
    private final AtomicInteger pileNumber = new AtomicInteger();

    /**
     * @param priceTable
     * @return
     */
    public List<Pile> createPiles(List<List<Integer>> priceTable) {
	CopyOnWriteArrayList<Pile> piles = new CopyOnWriteArrayList<>();
	for (List<Integer> pilePrices : priceTable) {
	    piles.add(buildPile(pilePrices));
	}
	return piles;
    }

    /**
     * @param pilePrices
     * @return
     */
    private Pile buildPile(List<Integer> pilePrices) {
	Pile pile = new Pile();
	pile.setOrderNumber(pileNumber.incrementAndGet());
	pile.setFluts(getFluts(pilePrices));
	return pile;
    }

    /**
     * @param pilePrices
     * @return
     */
    private List<Flut> getFluts(List<Integer> pilePrices) {
	List<Flut> fluts = new ArrayList<>();
	for (int price : pilePrices) {
	    Flut flut = new Flut();
	    flut.setOrderNumber(flutNumber.incrementAndGet());
	    flut.setBuyingPrice(BigDecimal.valueOf(price));
	    fluts.add(flut);
	}
	return fluts;
    }
}
