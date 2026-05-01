package com.p2pagent.agent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2pagent.transport.PartialMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageParser {

    private final ObjectMapper objectMapper;

    public MessageParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public AgentMessage parse(String body, String fromPeerId) throws JsonProcessingException {

        PartialMessage partial = objectMapper.readValue(body, PartialMessage.class);

        return new AgentMessage(
                UUID.randomUUID().toString(),
                partial.getOrderId(),
                partial.getType(),
                partial.getPayload(),
                fromPeerId,
                System.currentTimeMillis()
        );
    }
}