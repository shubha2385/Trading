package com.degiro.trading.optimizers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.degiro.trading.model.Flut;
import com.degiro.trading.model.OptimizationResult;
import com.degiro.trading.model.Pile;
import com.degiro.trading.model.Result;

public class ProfitOptimizer {

    private final List<Pile> piles;

    public ProfitOptimizer(List<Pile> piles) {
	this.piles = piles;
    }

    /**
     * @return
     */
    public List<OptimizationResult> optimize() {
	List<OptimizationResult> optimizationResults = new ArrayList<>();
	piles.forEach(pile -> {
	    List<Result> financialResults = calculateFinancialResults(pile);

	    Result optimalFinancialResult = findOptimalFinancialResult(financialResults);

	    List<Integer> orderOfFlutsToBuy = findOrderOfFlutsToBuy(financialResults, optimalFinancialResult);

	    boolean isNumberOfFlutesUniquelyDetermined = isNumberOfFlutsUniquelyDetermined(financialResults,
		    optimalFinancialResult);

	    optimizationResults.add(builldOptimizedResult(pile, optimalFinancialResult, orderOfFlutsToBuy,
		    isNumberOfFlutesUniquelyDetermined));
	});
	return optimizationResults;
    }

    /**
     * @param pile
     * @param optimalFinancialResult
     * @param orderOfFlutsToBuy
     * @param isNumberOfFlutesUniquelyDetermined
     * @return
     */
    private OptimizationResult builldOptimizedResult(Pile pile, Result optimalFinancialResult,
	    List<Integer> orderOfFlutsToBuy, boolean isNumberOfFlutesUniquelyDetermined) {
	OptimizationResult optimizationResult = new OptimizationResult();
	optimizationResult.setPileNumber(pile.getOrderNumber());
	optimizationResult.setProfit(optimalFinancialResult.getAccumulatedProfit());
	optimizationResult.setNumberOfFlutsUniquelyDetermined(isNumberOfFlutesUniquelyDetermined);
	optimizationResult.setOrderOfFlutsToBuy(orderOfFlutsToBuy);
	return optimizationResult;
    }

    /**
     * @param pile
     * @return
     */
    private List<Result> calculateFinancialResults(Pile pile) {
	return pile.getFluts().stream().map(flut -> {
	    BigDecimal accumulatedProfit = pile.getFluts().stream()
		    .filter(f -> f.getOrderNumber() <= flut.getOrderNumber()).map(Flut::getProfit)
		    .reduce(BigDecimal.ZERO, BigDecimal::add);
	    Result result = new Result();
	    result.setFlutOrderNumber(flut.getOrderNumber());
	    result.setAccumulatedProfit(accumulatedProfit);
	    return result;
	}).collect(Collectors.toList());
    }

    /**
     * @param financialResults
     * @return
     */
    private Result findOptimalFinancialResult(List<Result> financialResults) {
	return financialResults.stream().filter(result -> {
	    BigDecimal maxProfit = financialResults.stream().map(Result::getAccumulatedProfit)
		    .max(BigDecimal::compareTo).orElseThrow(() -> new IllegalStateException("Max profit not found"));

	    return result.getAccumulatedProfit().compareTo(maxProfit) == 0;
	}).min(Comparator.comparing(Result::getFlutOrderNumber, Integer::compareTo))
		.orElseThrow(() -> new IllegalStateException("Optimal financial result not found"));
    }

    /**
     * @param financialResults
     * @param optimalFinancialResult
     * @return
     */
    private List<Integer> findOrderOfFlutsToBuy(List<Result> financialResults, Result optimalFinancialResult) {
	return financialResults.stream().map(Result::getFlutOrderNumber)
		.filter(orderNumber -> orderNumber <= optimalFinancialResult.getFlutOrderNumber())
		.collect(Collectors.toList());
    }

    /**
     * @param financialResults
     * @param optimalFinancialResult
     * @return
     */
    private boolean isNumberOfFlutsUniquelyDetermined(List<Result> financialResults, Result optimalFinancialResult) {
	return financialResults.stream().map(Result::getAccumulatedProfit)
		.filter(profit -> profit.compareTo(optimalFinancialResult.getAccumulatedProfit()) == 0).count() == 1;
    }
}
