package com.p2pagent.tools;

import com.p2pagent.agent.context.AgentContext;
import com.p2pagent.agent.AgentMessage;
import com.p2pagent.messaging.MessageBus;
import com.p2pagent.agent.payload.ChatPayload;
import com.p2pagent.shared.MessageType;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommunicationTools {

    private final MessageBus messageBus;
    private final AgentContext agentContext;

    public CommunicationTools(MessageBus messageBus,
                              AgentContext agentContext) {
        this.messageBus = messageBus;
        this.agentContext = agentContext;
    }

    @Tool("""
    Send a chat message to another agent in the simulation.

    Use this ONLY for direct communication between agents.
    Do not include reasoning or system notes in the message.
    Keep it short and actionable.
    """)
    public void sendChatMessage(String toPeerId, String message) {

        AgentMessage<ChatPayload> msg = AgentMessage.of(
                UUID.randomUUID().toString(),
                null,
                MessageType.CHAT,
                new ChatPayload(message),
                agentContext.getPeerId(),
                toPeerId
        );

        messageBus.send(msg);
    }
}