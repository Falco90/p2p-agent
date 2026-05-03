package com.p2pagent.simulation;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AgentStateInitializer {

    public AgentState init(MotivationProperties props) {

        AgentState state = new AgentState();

        apply(state, "base", props.getBase());
        apply(state, "stock", props.getStock());
        apply(state, "threshold", props.getThreshold());

        return state;
    }

    private void apply(AgentState state,
                       String prefix,
                       Map<String, Integer> map) {

        if (map == null) return;

        map.forEach((k, v) ->
                state.set(prefix + "." + k, v));
    }
}