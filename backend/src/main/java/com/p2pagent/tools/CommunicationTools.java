package com.p2pagent.tools;

import com.p2pagent.agent.ChatService;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class CommunicationTools {

    private final ChatService chatService;

    public CommunicationTools(ChatService chatService) {
        this.chatService = chatService;
    }

    @Tool("""
            Send a chat message to another agent.
            This triggers a distributed AXL message.
            """)
    public void sendChatMessage(String toPeerId, String message) {
        chatService.sendChatMessage(toPeerId, message);
    }
}