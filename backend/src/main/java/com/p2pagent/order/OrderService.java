package com.p2pagent.order;

import com.p2pagent.axl.AxlClient;
import com.p2pagent.axl.AxlProperties;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private final AxlClient axlClient;
    private final String peerId;

    public OrderService(AxlClient axlClient, AxlProperties axlProperties) {
        this.axlClient = axlClient;
        this.peerId = axlProperties.getPeerId();
    }

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public void handleEvent(OrderEvent event) {

        Order order = orders.computeIfAbsent(
                event.orderId(),
                id -> new Order(id, event.fromPeerId(), event.toPeerId())
        );

        order.apply(event);

        System.out.println("Order updated: " + order.getState());

        switch (event.type()) {

            case SERVICE_REQUEST -> {
                if (isSeller(event)) {
                    sendOrderAccepted(event);
                }
            }
        }
    }

    private void sendOrderAccepted(OrderEvent event) {

        String json = """
                {
                  "type": "ORDER_ACCEPTED",
                  "orderId": "%s",
                  "payload": "Accepted, preparing your order"
                }
                """.formatted(event.orderId());

        axlClient.send(event.fromPeerId(), json);

        System.out.println("[Seller] Sent ORDER_ACCEPTED " + event.orderId());
    }

    private boolean isSeller(OrderEvent event) {
        return peerId.equals(event.toPeerId());
    }

    private boolean isBuyer(OrderEvent event) {
        return peerId.equals(event.fromPeerId());
    }
}