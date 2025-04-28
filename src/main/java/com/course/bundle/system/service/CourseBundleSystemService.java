package com.course.bundle.system.service;

import com.course.bundle.system.config.ProviderConfigLoader;
import com.course.bundle.system.model.ProviderTopicsConfig;
import com.course.bundle.system.model.BundleResponse;
import com.course.bundle.system.model.BundleRequest;
import com.course.bundle.system.service.strategy.MultipleTopicsStrategy;
import com.course.bundle.system.service.strategy.SingleTopicStrategy;
import com.course.bundle.system.service.strategy.TopicsCalculationStrategy;
import com.course.bundle.system.service.strategy.TopicsCalculationStrategyContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseBundleSystemService {

    private final ProviderConfigLoader configLoader;

    public List<BundleResponse> generateBundle(BundleRequest request) throws Exception {
        log.info("CourseBundleSystemService->generateBundle: Start generate bundle");
        Map<String, Integer> topics = request.getTopics();
        if (topics == null || topics.isEmpty()) {
            throw new IllegalArgumentException("Topics cannot be empty");
        }
        List<Map.Entry<String, Integer>> topTopics = retrieveTopTopics(topics);
        ProviderTopicsConfig config = configLoader.loadConfig();
        List<BundleResponse> bundles = new ArrayList<>();
        for (Map.Entry<String, String> provider : config.getProvider_topics().entrySet()) {
            String providerName = provider.getKey();
            double bundle = getBundle(provider, topTopics);
            if (bundle > 0) {
                bundles.add(new BundleResponse(providerName, bundle));
            }
        }
        log.info("CourseBundleSystemService->generateBundle: Finished generate bundle");
        return bundles;
    }

    private double getBundle(Map.Entry<String, String> provider, List<Map.Entry<String, Integer>> topTopics) throws Exception {
        Set<String> offeredTopics = new HashSet<>(Arrays.asList(provider.getValue().split("\\+")));
        List<Map.Entry<String, Integer>> matchingTopics = retrieveMatchingTopics(topTopics, offeredTopics);
        TopicsCalculationStrategy strategy;
        if (matchingTopics.size() >= 2) {
            strategy = new MultipleTopicsStrategy();
        } else if (matchingTopics.size() == 1) {
            strategy = new SingleTopicStrategy();
        } else {
            throw new Exception("Strategy for calculate topics not found");
        }
        TopicsCalculationStrategyContext context = new TopicsCalculationStrategyContext(strategy);
        return context.execute(matchingTopics, topTopics);
    }

    private List<Map.Entry<String, Integer>> retrieveTopTopics(Map<String, Integer> topics) {
        return topics.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(3)
                .toList();
    }

    private List<Map.Entry<String, Integer>> retrieveMatchingTopics(List<Map.Entry<String, Integer>> topTopics, Set<String> offeredTopics) {
        return topTopics.stream()
                .filter(entry -> offeredTopics.contains(entry.getKey()))
                .toList();
    }
}
