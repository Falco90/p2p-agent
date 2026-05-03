package com.p2pagent.simulation;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StateUpdater {

    private final MotivationProperties props;

    public StateUpdater(MotivationProperties props) {
        this.props = props;
    }

    public void update(AgentState state) {

        Map<String, MotivationProperties.Drift> drift = props.getDrift();
        if (drift == null) return;

        for (Map.Entry<String, MotivationProperties.Drift> entry : drift.entrySet()) {

            String key = entry.getKey();
            MotivationProperties.Drift range = entry.getValue();

            int delta = random(range.getMin(), range.getMax());

            state.increment(key, delta);
        }
    }

    private int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}