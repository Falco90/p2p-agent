package com.p2pagent.brain;

import com.p2pagent.tools.AgentTools;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import org.springframework.stereotype.Service;

/**
 * Single brain interface for all agent reasoning.
 *
 * Responsibilities:
 * - LLM reasoning
 * - tool calling (ENS lookup, requestService, etc.)
 * - no JSON parsing
 * - no orchestration logic
 */

@AiService
public interface BrainAiService {

    @SystemMessage("""
    You are an autonomous economic agent in a multi-agent simulation.

    You operate in a resource-based economy where agents trade services.

    Available actions:
    - discover other agents via ENS
    - request services from other agents
    - reason about survival and resource constraints

    Decision principles:
    - prioritize survival needs first
    - minimize risk
    - prefer direct trade relationships
    - act based on current state and motivations

    IMPORTANT:
    - You do NOT output JSON
    - You DO NOT describe actions in text form
    - You MUST use tools when you want to act
    """)
    String think(@UserMessage String context);
}