package com.p2pagent.simulation;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StateNormalizer {

    public Map<String, Object> normalize(AgentState state) {

        Map<String, Integer> raw = state.snapshot();
        Map<String, Object> out = new HashMap<>();

        raw.forEach((k, v) -> {
            String clean = k
                    .replace("stock.", "")
                    .replace("threshold.", "")
                    .replace("base.", "");

            out.put(clean, v);
        });

        return out;
    }

    public Map<String, Object> normalizeThresholds(AgentState state) {

        Map<String, Object> out = new HashMap<>();

        state.snapshot().forEach((k, v) -> {
            if (k.startsWith("threshold.")) {
                String clean = k.replace("threshold.", "");
                out.put(clean, v);
            }
        });

        return out;
    }
}