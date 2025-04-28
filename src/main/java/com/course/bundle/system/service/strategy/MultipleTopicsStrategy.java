package com.course.bundle.system.service.strategy;

import java.util.List;
import java.util.Map;

public class MultipleTopicsStrategy implements TopicsCalculationStrategy {

    @Override
    public Double calculate(List<Map.Entry<String, Integer>> matchingTopics, List<Map.Entry<String, Integer>> topTopics) {
        int sum = matchingTopics.stream()
                .mapToInt(Map.Entry::getValue)
                .sum();
        return sum * 0.10;
    }
}
