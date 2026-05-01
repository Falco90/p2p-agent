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
                id -> createOrderFromEvent(event)
        );

        System.out.println("Applying event type: " + event.type());
        order.apply(event);

        System.out.println("Order updated: " + order.getState());

        switch (event.type()) {

            case SERVICE_REQUEST -> {
                if (isSeller(order)) {
                    sendOrderAccepted(event);
                }
            }

            case ORDER_ACCEPTED -> {
                System.out.println("My peer id: " + peerId);
                if (isBuyer(order)) {
                    sendPayment(event);
                } else {
                    System.out.println("isBuyer is false");
                }
            }

            case PAYMENT_SENT -> {
                if (isSeller(order)) {
                    sendOrderCompleted(event);
                }
            }
        }
    }

    private Order createOrderFromEvent(OrderEvent event) {

        if (event.type() == OrderEvent.OrderEventType.SERVICE_REQUEST) {
            return new Order(
                    event.orderId(),
                    event.fromPeerId(), // buyer
                    event.toPeerId()    // seller
            );
        }

        // fallback (should not really happen if flow is correct)
        return new Order(
                event.orderId(),
                event.toPeerId(),
                event.fromPeerId()
        );
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

    private void sendPayment(OrderEvent event) {

        String json = """
                {
                  "type": "PAYMENT_SENT",
                  "orderId": "%s",
                  "payload": "5 credits"
                }
                """.formatted(event.orderId());

        axlClient.send(event.fromPeerId(), json);

        System.out.println("[Buyer] Sent PAYMENT " + event.orderId());
    }

    private void sendOrderCompleted(OrderEvent event) {

        String json = """
                {
                  "type": "ORDER_COMPLETED",
                  "orderId": "%s",
                  "payload": "Order ready for pickup"
                }
                """.formatted(event.orderId());

        axlClient.send(event.fromPeerId(), json);

        System.out.println("[Seller] Sent ORDER_COMPLETED " + event.orderId());
    }

    private boolean isBuyer(Order order) {
        return peerId.equals(order.getBuyerPeerId());
    }

    private boolean isSeller(Order order) {
        return peerId.equals(order.getSellerPeerId());
    }
}