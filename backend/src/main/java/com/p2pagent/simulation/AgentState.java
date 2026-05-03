package com.p2pagent.simulation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AgentState {

    private final Map<String, Integer> values = new ConcurrentHashMap<>();

    public void set(String key, int value) {
        values.put(key, value);
    }

    public int get(String key) {
        return values.getOrDefault(key, 0);
    }

    public void increment(String key, int delta) {
        values.merge(key, delta, Integer::sum);
    }

    public Map<String, Integer> snapshot() {
        return Map.copyOf(values);
    }
}