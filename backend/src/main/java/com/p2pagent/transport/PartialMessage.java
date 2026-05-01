package com.p2pagent.transport;

import com.p2pagent.shared.MessageType;

public class PartialMessage {
    private String orderId;
    private MessageType type;
    private String payload;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
