package com.course.bundle.system.service.strategy;

import java.util.List;
import java.util.Map;

public class SingleTopicStrategy implements TopicsCalculationStrategy {

    @Override
    public Double calculate(List<Map.Entry<String, Integer>> matchingTopics, List<Map.Entry<String, Integer>> topTopics) {
        Map.Entry<String, Integer> match = matchingTopics.get(0);
        int position = topTopics.indexOf(match);

        return switch (position) {
            case 0 -> match.getValue() * 0.20;
            case 1 -> match.getValue() * 0.25;
            case 2 -> match.getValue() * 0.30;
            default -> 0.0;
        };
    }
}
