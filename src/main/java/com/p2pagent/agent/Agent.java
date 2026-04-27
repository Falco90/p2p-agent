package com.p2pagent.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Agent {

    @SystemMessage("You are an agent operating on the Ethereum network")
    String chat(String userMessage);
}
