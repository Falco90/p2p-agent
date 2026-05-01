package com.p2pagent.agent;

import com.p2pagent.shared.MessageType;

public record AgentMessage(
        String id,
        String orderId,
        MessageType type,
        String payload,
        String fromPeerId,
        long timestamp
) {
    public static AgentMessage of(
            String id,
            String orderId,
            MessageType type,
            String payload,
            String fromPeerId
    ) {
        return new AgentMessage(
                id,
                orderId,
                type,
                payload,
                fromPeerId,
                System.currentTimeMillis()
        );
    }
}