package com.p2pagent.messaging;

import com.p2pagent.agent.AgentMessage;
import com.p2pagent.agent.ChatService;
import com.p2pagent.agent.payload.ChatPayload;
import com.p2pagent.shared.MessageType;
import org.springframework.stereotype.Component;

@Component
public class MessageBus {

    private final ChatService chatService;

    public MessageBus(ChatService chatService) {
        this.chatService = chatService;
    }

    public void send(AgentMessage<?> msg) {

        if (msg.type() == MessageType.CHAT) {
            chatService.handle((AgentMessage<ChatPayload>) msg);
        }
    }
}