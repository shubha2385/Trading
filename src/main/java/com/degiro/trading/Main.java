package com.degiro.trading;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.degiro.trading.constants.General;
import com.degiro.trading.helpers.InputHandler;
import com.degiro.trading.helpers.OutputHandler;
import com.degiro.trading.model.TestCaseInput;
import com.degiro.trading.model.TestCaseOutput;
import com.degiro.trading.optimizers.OptimizationTask;

public class Main {

    public static void main(String[] args) {
	try (Scanner scanner = new Scanner(System.in)) {

	    List<Future<TestCaseOutput>> testCaseResults = new CopyOnWriteArrayList<>();

	    String ch = scanner.nextLine();

	    while (!General.END_OF_INPUT.equals(ch)) {
		Thread thread = new Thread(buildTask(testCaseResults, buildOptimizationTask(scanner, ch)));
		thread.start();

		ch = scanner.nextLine();
	    }

	    OutputHandler.printTestCaseResults(testCaseResults);
	}
    }

    /**
     * @param testCaseResults
     * @param optimizationTask
     * @return
     */
    private static FutureTask<TestCaseOutput> buildTask(List<Future<TestCaseOutput>> testCaseResults,
	    OptimizationTask optimizationTask) {
	FutureTask<TestCaseOutput> futureTask = new FutureTask<>(optimizationTask);
	testCaseResults.add(futureTask);
	return futureTask;
    }

    /**
     * @param scanner
     * @param ch
     * @return
     */
    private static OptimizationTask buildOptimizationTask(final Scanner scanner, String ch) {
	List<List<Integer>> priceTable = InputHandler.readTestCase(scanner, Integer.parseInt(ch));
	TestCaseInput testCaseInput = new TestCaseInput(priceTable);
	return new OptimizationTask(testCaseInput);
    }

}
