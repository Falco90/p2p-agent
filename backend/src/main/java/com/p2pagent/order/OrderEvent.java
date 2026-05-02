package com.p2pagent.order;

import com.p2pagent.agent.AgentMessage;

import java.time.Instant;

public record OrderEvent<T>(
        String orderId,
        OrderEventType type,
        T payload,
        String fromPeerId,
        String toPeerId,
        Instant timestamp
) {

    public enum OrderEventType {
        SERVICE_REQUEST,
        ORDER_ACCEPTED,
        PAYMENT_CONFIRMED,
        ORDER_COMPLETED,
        CANCELLED,
        ORDER_STATUS
    }

    public static <T> OrderEvent<T> fromAgentMessage(
            AgentMessage<T> msg,
            String localPeerId
    ) {
        return new OrderEvent<>(
                msg.orderId(),
                mapType(msg.type()),
                msg.payload(),
                msg.fromPeerId(),
                localPeerId,
                java.time.Instant.now()
        );
    }

    private static OrderEventType mapType(com.p2pagent.shared.MessageType type) {
        return switch (type) {
            case SERVICE_REQUEST -> OrderEventType.SERVICE_REQUEST;
            case ORDER_ACCEPTED -> OrderEventType.ORDER_ACCEPTED;
            case PAYMENT_CONFIRMED -> OrderEventType.PAYMENT_CONFIRMED;
            case ORDER_COMPLETED -> OrderEventType.ORDER_COMPLETED;
            default -> OrderEventType.ORDER_STATUS;
        };
    }
}