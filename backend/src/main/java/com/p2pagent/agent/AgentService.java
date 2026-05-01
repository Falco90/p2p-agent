package com.p2pagent.agent;

import com.p2pagent.axl.AxlClient;
import com.p2pagent.shared.OrderIdGenerator;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final AxlClient axlClient;
    private final OrderIdGenerator orderIdGenerator;

    public AgentService(AxlClient axlClient, OrderIdGenerator orderIdGenerator) {
        this.axlClient = axlClient;
        this.orderIdGenerator = orderIdGenerator;
    }

    public String requestService(String sellerPeerId, String description) {
        String orderId = orderIdGenerator.generate();

        String json = """
        {
          "type": "SERVICE_REQUEST",
          "orderId": "%s",
          "payload": "%s"
        }
        """.formatted(orderId, description);

        axlClient.send(sellerPeerId, json);

        return "Created order " + orderId;
    }
}