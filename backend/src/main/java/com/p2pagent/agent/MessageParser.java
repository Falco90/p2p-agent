package com.p2pagent.agent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2pagent.agent.payload.ChatPayload;
import com.p2pagent.axl.AxlProperties;
import com.p2pagent.order.payload.*;

import com.p2pagent.shared.MessageType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageParser {

    private final ObjectMapper objectMapper;
    private final AxlProperties axlProperties;

    public MessageParser(ObjectMapper objectMapper, AxlProperties axlProperties) {
        this.objectMapper = objectMapper;
        this.axlProperties = axlProperties;
    }

    public AgentMessage<?> parse(String json, String fromPeerId) {

        try {
            JsonNode root = objectMapper.readTree(json);

            MessageType type = MessageType.valueOf(root.get("type").asText());
            String orderId = root.get("orderId").asText();

            JsonNode payloadNode = root.get("payload");

            Object payload = switch (type) {

                case CHAT ->
                        objectMapper.treeToValue(payloadNode, ChatPayload.class);

                case SERVICE_REQUEST ->
                        objectMapper.treeToValue(payloadNode, ServiceRequestPayload.class);

                case ORDER_ACCEPTED ->
                        objectMapper.treeToValue(payloadNode, OrderAcceptedPayload.class);

                case PAYMENT_CONFIRMED ->
                        objectMapper.treeToValue(payloadNode, PaymentConfirmedPayload.class);

                case ORDER_COMPLETED ->
                        objectMapper.treeToValue(payloadNode, OrderCompletedPayload.class);

                case QUOTE ->
                        objectMapper.treeToValue(payloadNode, QuotePayload.class);

                case CANCELLED ->
                        objectMapper.treeToValue(payloadNode, CancelledPayload.class);

                default ->
                        throw new RuntimeException("Unknown message type: " + type);
            };

            return AgentMessage.of(UUID.randomUUID().toString(), orderId, type, payload, fromPeerId, axlProperties.getPeerId());

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse message", e);
        }
    }
}