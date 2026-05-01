package com.p2pagent.agent;

import dev.langchain4j.agent.tool.Tool;
import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class AgentTools {
    private final AgentService agentService;

    public AgentTools(AgentService agentService) {
        this.agentService = agentService;
    }

    @Tool("Request a service from another agent. Provide peerId and what you want.")
    public String requestService(String peerId, String description) {

        return agentService.requestService(peerId, description);
    }
}