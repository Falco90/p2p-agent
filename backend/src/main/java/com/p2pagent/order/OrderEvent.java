package com.p2pagent.order;

import com.p2pagent.agent.AgentMessage;

import java.time.Instant;

public record OrderEvent(
        String orderId,
        OrderEventType type,
        String payload,
        String fromPeerId,
        String toPeerId,
        Instant timestamp
) {

    public OrderEvent(
            String orderId,
            OrderEventType type,
            String payload,
            String fromPeerId,
            String toPeerId
    ) {
        this(orderId, type, payload, fromPeerId, toPeerId, Instant.now());
    }

    public enum OrderEventType {
        SERVICE_REQUEST,
        QUOTE_SENT,
        ORDER_ACCEPTED,
        PAYMENT_SENT,
        PAYMENT_CONFIRMED,
        CANCELLED,
        ORDER_STATUS
    }

    public static OrderEvent request(AgentMessage msg, String localPeerId) {
        return new OrderEvent(
                msg.orderId(),
                OrderEventType.SERVICE_REQUEST,
                msg.payload(),
                msg.fromPeerId(),
                localPeerId,
                Instant.now()
        );
    }

    public static OrderEvent payment(AgentMessage msg, String localPeerId) {
        return new OrderEvent(
                msg.orderId(),
                OrderEventType.PAYMENT_SENT,
                msg.payload(),
                msg.fromPeerId(),
                localPeerId,
                Instant.now()
        );
    }

    public static OrderEvent ignore(AgentMessage msg, String localPeerId) {
        return new OrderEvent(
                msg.orderId(),
                OrderEventType.ORDER_STATUS,
                msg.payload(),
                msg.fromPeerId(),
                localPeerId,
                Instant.now()
        );
    }
}