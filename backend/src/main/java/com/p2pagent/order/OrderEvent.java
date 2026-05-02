package com.p2pagent.order;

import com.p2pagent.agent.AgentMessage;

import java.time.Instant;

public record OrderEvent(
        String orderId,
        OrderEventType type,
        Object payload,
        String fromPeerId,
        String toPeerId,
        Instant timestamp
) {

    public OrderEvent(
            String orderId,
            OrderEventType type,
            Object payload,
            String fromPeerId,
            String toPeerId
    ) {
        this(orderId, type, payload, fromPeerId, toPeerId, Instant.now());
    }

    public enum OrderEventType {
        SERVICE_REQUEST,
        QUOTE_SENT,
        ORDER_ACCEPTED,
        ORDER_COMPLETED,
        PAYMENT_CONFIRMED,
        CANCELLED,
        ORDER_STATUS
    }

    public static OrderEvent fromAgentMessage(AgentMessage msg, String localPeerId) {

        return new OrderEvent(
                msg.orderId(),
                mapType(msg.type()),
                msg.payload(),
                msg.fromPeerId(),
                localPeerId,
                Instant.now()
        );
    }

    private static OrderEventType mapType(com.p2pagent.shared.MessageType type) {

        return switch (type) {
            case SERVICE_REQUEST -> OrderEventType.SERVICE_REQUEST;
            case ORDER_ACCEPTED -> OrderEventType.ORDER_ACCEPTED;
            case PAYMENT_CONFIRMED -> OrderEventType.PAYMENT_CONFIRMED;
            case ORDER_COMPLETED -> OrderEventType.ORDER_COMPLETED;
            case CANCELLED -> OrderEventType.CANCELLED;
            default -> OrderEventType.ORDER_STATUS;
        };
    }
}