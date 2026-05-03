package com.p2pagent.simulation;

import com.p2pagent.agent.AgentProperties;
import com.p2pagent.brain.BrainAiService;
import com.p2pagent.simulation.AgentState;
import com.p2pagent.simulation.StateNormalizer;
import com.p2pagent.tools.AgentTools;
import org.springframework.stereotype.Component;

@Component
public class AgentDecisionEngine {

    private final StateNormalizer normalizer;
    private final AgentTools tools;
    private final BrainAiService brain;
    private final AgentProperties agentProperties;

    public AgentDecisionEngine(
                               StateNormalizer normalizer,
                               AgentTools tools,
                               BrainAiService brain,
                               AgentProperties agentProperties) {

        this.normalizer = normalizer;
        this.tools = tools;
        this.brain = brain;
        this.agentProperties = agentProperties;
    }

    public void tick(AgentState state) {

        String context = """
        ROLE: %s
        STATE: %s
        """.formatted(
                agentProperties.getRole(),
                normalizer.normalize(state)
        );

        String result = brain.think(context);

        System.out.println("[THOUGHT][" + agentProperties.getRole() + "] " + result);
    }
}