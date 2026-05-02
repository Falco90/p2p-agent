package com.p2pagent.agent;

import com.p2pagent.agent.payload.ChatPayload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService {

    private final ConcurrentHashMap<String, List<ChatMessage>> chats = new ConcurrentHashMap<>();

    public void handle(AgentMessage<?> msg) {

        if (!(msg.payload() instanceof ChatPayload(String message))) {
            System.out.println("Invalid chat payload: " + msg.payload());
            return;
        }

        ChatMessage chat = new ChatMessage(
                msg.fromPeerId(),
                message,
                System.currentTimeMillis()
        );

        chats.computeIfAbsent(msg.fromPeerId(), k -> new ArrayList<>())
                .add(chat);

        System.out.println("[CHAT] " + chat);
    }

    public List<ChatMessage> getConversation(String peerId) {
        return chats.getOrDefault(peerId, List.of());
    }
}