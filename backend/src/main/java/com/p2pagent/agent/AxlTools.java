package com.p2pagent.agent;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class AxlTools {

    private final AxlClient axlClient;

    public AxlTools(AxlClient axlClient) {
        this.axlClient = axlClient;
    }

    @Tool("Send a message to another AXL node using its peer id")
    public String sendMessageToNode(String peerId, String message) {
        try {
            axlClient.send(peerId, message);

            return "Message sent successfully to peer " + peerId;

        } catch (Exception e) {
            return "Failed to send message: " + e.getMessage();
        }
    }
}