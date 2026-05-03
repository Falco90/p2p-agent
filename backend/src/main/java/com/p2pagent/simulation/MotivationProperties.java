package com.p2pagent.simulation;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "agent.motivation")
public class MotivationProperties {

    private Map<String, Integer> base;
    private Map<String, Integer> stock;
    private Map<String, Integer> threshold;

    private Map<String, Drift> drift;

    public static class Drift {
        private int min;
        private int max;

        public int getMin() { return min; }
        public void setMin(int min) { this.min = min; }

        public int getMax() { return max; }
        public void setMax(int max) { this.max = max; }
    }

    public Map<String, Integer> getBase() { return base; }
    public void setBase(Map<String, Integer> base) { this.base = base; }

    public Map<String, Integer> getStock() { return stock; }
    public void setStock(Map<String, Integer> stock) { this.stock = stock; }

    public Map<String, Integer> getThreshold() { return threshold; }
    public void setThreshold(Map<String, Integer> threshold) { this.threshold = threshold; }

    public Map<String, Drift> getDrift() { return drift; }
    public void setDrift(Map<String, Drift> drift) { this.drift = drift; }
}