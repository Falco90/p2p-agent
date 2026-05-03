package com.p2pagent.agent;

import com.p2pagent.axl.AxlProperties;
import com.p2pagent.brain.BrainAiService;
import org.springframework.stereotype.Component;

@Component
public class AgentLoop {

    private final BrainAiService brain;
    private final AgentProperties agentProperties;
    private final AxlProperties axlProperties;

    public AgentLoop(BrainAiService brain,
                     AgentProperties agentProperties, AxlProperties axlProperties) {
        this.brain = brain;
        this.agentProperties = agentProperties;
        this.axlProperties = axlProperties;
    }

    public void onEvent(String input) {

        String enrichedContext = """
        You are %s.

        Current state:
        %s

        Event:
        %s

        Decide what to do.
        """.formatted(
                agentProperties.getRole(),
                contextSnapshot(),
                input
        );

        brain.think(enrichedContext);
    }

    private String contextSnapshot() {
        return "peerId=" + axlProperties.getPeerId();
    }
}