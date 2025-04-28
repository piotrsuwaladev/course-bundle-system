package com.course.bundle.system.service.strategy;

import java.util.List;
import java.util.Map;

public interface TopicsCalculationStrategy {

    Double calculate(List<Map.Entry<String, Integer>> matchingTopics, List<Map.Entry<String, Integer>> topTopics);
}
