package com.p2pagent.agent;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "agent.motivation")
public class MotivationProperties {

    private Map<String, Integer> stock;

    private Map<String, Integer> threshold;

    private Integer threatLevel;

    public Map<String, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<String, Integer> stock) {
        this.stock = stock;
    }

    public Map<String, Integer> getThreshold() {
        return threshold;
    }

    public void setThreshold(Map<String, Integer> threshold) {
        this.threshold = threshold;
    }

    public Integer getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(Integer threatLevel) {
        this.threatLevel = threatLevel;
    }

    @Override
    public String toString() {
        return "MotivationProperties{" +
                "stock=" + stock +
                ", threshold=" + threshold +
                ", threatLevel=" + threatLevel +
                '}';
    }
}