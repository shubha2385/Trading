package com.degiro.trading.helpers;

import java.util.List;
import java.util.concurrent.Future;

import com.degiro.trading.constants.General;
import com.degiro.trading.model.OptimizationResult;
import com.degiro.trading.model.TestCaseOutput;

public final class OutputHandler {

    private OutputHandler() {

    }

    /**
     * @param testCaseResults
     */
    public static void printTestCaseResults(List<Future<TestCaseOutput>> testCaseResults) {
	testCaseResults.forEach(testCaseResult -> {
	    try {
		List<OptimizationResult> optimizationResults = testCaseResult.get().getOptimizationResults();
		printOptimizationResults(optimizationResults);

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	});
    }

    /**
     * @param optimizationResults
     */
    private static void printOptimizationResults(List<OptimizationResult> optimizationResults) {
	optimizationResults.forEach(result -> {
	    System.out.printf("schuurs %d\n", result.getPileNumber());
	    System.out.printf("Maximum profit is %2.0f.\n", result.getProfit().floatValue());

	    printNumberOfFluts(result);
	    System.out.println();
	});
	System.out.println();
    }

    /**
     * @param optimizationResult
     */
    private static void printNumberOfFluts(OptimizationResult optimizationResult) {
	System.out.print("Number of fluts to buy: ");
	if (!optimizationResult.isPositiveProfit()) {
	    System.out.print(General.ZERO_ITEMS_TO_BUY);
	} else if (optimizationResult.isNumberOfFlutsUniquelyDetermined()) {
	    System.out.printf("%d", optimizationResult.getNumberOfFlutsToBuy());
	} else {
	    optimizationResult.getOrderOfFlutsToBuy(General.MAX_NUMBER_OF_FLUTS_TO_SHOW).forEach(number -> {
		System.out.printf("%d ", number);
	    });
	}
    }
}
