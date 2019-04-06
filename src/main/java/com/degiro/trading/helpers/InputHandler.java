package com.degiro.trading.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

public final class InputHandler {

    private InputHandler() {

    }

    /**
     * @param scanner
     * @param numberOfPiles
     * @return
     */
    public static final List<List<Integer>> readTestCase(final Scanner scanner, int numberOfPiles) {
	CopyOnWriteArrayList<List<Integer>> priceTable = new CopyOnWriteArrayList<>();

	for (int i = 0; i < numberOfPiles; i++) {
	    StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine());
	    ArrayList<Integer> priceRow = new ArrayList<>(tokenizer.countTokens());
	    while (tokenizer.hasMoreTokens()) {
		priceRow.add(Integer.parseInt(tokenizer.nextToken()));
	    }
	    priceTable.add(priceRow);
	}
	return priceTable;
    }

}
