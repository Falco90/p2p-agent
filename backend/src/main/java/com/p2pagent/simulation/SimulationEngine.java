package com.p2pagent.simulation;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SimulationEngine {

    private final StateUpdater updater;
    private final AgentDecisionEngine engine;
    private final AgentState state;

    public SimulationEngine(StateUpdater updater,
                            AgentDecisionEngine engine,
                            AgentStateInitializer init,
                            MotivationProperties props) {

        this.updater = updater;
        this.engine = engine;
        this.state = init.init(props);
    }

    @PostConstruct
    public void start() {

        new Thread(() -> {

            while (true) {
                try {
                    updater.update(state);

                    System.out.println("[STATE] " + state.snapshot());

                    engine.tick(state);

                    Thread.sleep(30000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}