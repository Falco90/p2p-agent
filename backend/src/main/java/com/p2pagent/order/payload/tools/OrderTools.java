package com.p2pagent.order.payload.tools;

import com.p2pagent.order.payload.ServiceRequestPayload;
import com.p2pagent.axl.AxlClient;
import com.p2pagent.axl.AxlProperties;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.UUID;

@Component
public class OrderTools {

    private final AxlClient axlClient;
    private final String peerId;
    private final ObjectMapper objectMapper;

    public OrderTools(AxlClient axlClient,
                      ObjectMapper objectMapper, AxlProperties axlProperties) {

        this.axlClient = axlClient;
        this.peerId = axlProperties.getPeerId();
        this.objectMapper = objectMapper;
    }

    @Tool("Send a service request to another agent to buy an item")
    public String requestService(String sellerPeerId, String item, int quantity) {

        try {
            String orderId = "order-" + peerId.substring(0, 6) + "-" + UUID.randomUUID();

            ServiceRequestPayload payload = new ServiceRequestPayload(
                    "Requesting " + item,
                    item,
                    quantity
            );

            String json = objectMapper.writeValueAsString(Map.of(
                    "type", "SERVICE_REQUEST",
                    "orderId", orderId,
                    "payload", payload
            ));

            axlClient.send(sellerPeerId, json);

            return "Order request sent. Order ID: " + orderId;

        } catch (Exception e) {
            return "Failed to send request: " + e.getMessage();
        }
    }
}