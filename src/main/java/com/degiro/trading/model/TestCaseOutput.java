package com.degiro.trading.model;

import java.util.List;

public class TestCaseOutput {

    private final List<OptimizationResult> optimizationResults;

    public TestCaseOutput(List<OptimizationResult> optimizationResults) {
        this.optimizationResults = optimizationResults;
    }

    public List<OptimizationResult> getOptimizationResults() {
        return optimizationResults;
    }
}
