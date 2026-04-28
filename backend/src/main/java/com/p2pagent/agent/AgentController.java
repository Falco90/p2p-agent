package com.p2pagent.agent;

import dev.langchain4j.service.spring.AiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is an example of using an {@link AiService}, a high-level LangChain4j API.
 */
@RestController
public class AgentController {

    private final Agent agent;

    public AgentController(Agent agent) {
        this.agent = agent;
    }

    @GetMapping("/agent")
    public String agent(@RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
        return agent.chat(message);
    }
}
