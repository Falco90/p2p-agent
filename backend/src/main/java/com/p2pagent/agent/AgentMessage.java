package com.p2pagent.agent;

import com.p2pagent.shared.MessageType;

public record AgentMessage<T>(
        String id,
        String orderId,
        MessageType type,
        T payload,
        String fromPeerId,
        String toPeerId,
        long timestamp
) {

    public static <T> AgentMessage<T> of(
            String id,
            String orderId,
            MessageType type,
            T payload,
            String fromPeerId,
            String toPeerId
    ) {
        return new AgentMessage<>(
                id,
                orderId,
                type,
                payload,
                fromPeerId,
                toPeerId,
                System.currentTimeMillis()
        );
    }
}