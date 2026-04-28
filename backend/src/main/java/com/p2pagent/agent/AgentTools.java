package com.p2pagent.agent;

import dev.langchain4j.agent.tool.Tool;
import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class AgentTools {

    /**
     * This tool is available to {@link Agent}
     */
    @Tool
    @Observed
    public String currentTime() {
        return LocalTime.now().toString();
    }
}