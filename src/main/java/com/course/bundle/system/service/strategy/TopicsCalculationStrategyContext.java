package com.course.bundle.system.service.strategy;

import java.util.List;
import java.util.Map;

public class TopicsCalculationStrategyContext {

    private final TopicsCalculationStrategy strategy;

    public TopicsCalculationStrategyContext(TopicsCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public Double execute(List<Map.Entry<String, Integer>> matchingTopics, List<Map.Entry<String, Integer>> topTopics) {
        return strategy.calculate(matchingTopics, topTopics);
    }
}
