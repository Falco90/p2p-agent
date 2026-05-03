package com.p2pagent.agent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2pagent.axl.AxlClient;
import com.p2pagent.axl.AxlProperties;
import com.p2pagent.agent.payload.ChatPayload;
import com.p2pagent.brain.BrainAiService;
import com.p2pagent.discovery.AgentDiscoveryService;
import com.p2pagent.shared.MessageType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private final AxlProperties axlProperties;
    private final AxlClient axlClient;
    private final AgentDiscoveryService agentDiscoveryService;

    private final ConcurrentHashMap<String, List<ChatMessage>> chats = new ConcurrentHashMap<>();

    public ChatService(ObjectMapper objectMapper,
                       AxlProperties axlProperties, AxlClient axlClient, AgentDiscoveryService agentDiscoveryService) {
        this.objectMapper = objectMapper;
        this.axlProperties = axlProperties;
        this.axlClient = axlClient;
        this.agentDiscoveryService = agentDiscoveryService;
    }

    public void sendChatMessage(String toPeerId, String message) {
        try {
            AgentMessage<ChatPayload> msg = AgentMessage.of(
                    java.util.UUID.randomUUID().toString(),
                    null,
                    MessageType.CHAT,
                    new ChatPayload(message),
                    axlProperties.getPeerId(),
                    toPeerId
            );

            String json = objectMapper.writeValueAsString(msg);

            axlClient.send(toPeerId, json);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send chat message", e);
        }
    }

    public void handleIncoming(AgentMessage<?> msg) {
        if (msg.payload() == null) {
            System.out.println("[CHAT] Invalid message: null payload");
            return;
        }

        if (!(msg.payload() instanceof ChatPayload chatPayload)) {
            System.out.println("[CHAT] Invalid payload type: " + msg.payload());
            return;
        }

        String from = msg.fromPeerId();
        String message = chatPayload.message();

        ChatMessage chat = new ChatMessage(
                from,
                message,
                msg.timestamp()
        );

        chats.computeIfAbsent(from, k -> new ArrayList<>())
                .add(chat);

        System.out.println("[Incoming Chat: ] " + from + " → " + message);
    }

    public List<ChatMessage> getConversation(String peerId) {
        return chats.getOrDefault(peerId, List.of());
    }
}