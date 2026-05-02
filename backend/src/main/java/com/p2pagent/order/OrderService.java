package com.p2pagent.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2pagent.axl.AxlClient;
import com.p2pagent.axl.AxlProperties;
import com.p2pagent.order.payload.*;
import com.p2pagent.payment.Payment;
import com.p2pagent.payment.PaymentService;
import com.p2pagent.web3.WalletService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private final AxlClient axlClient;
    private final String peerId;
    private final PaymentService paymentService;
    private final WalletService walletService;
    private final ObjectMapper objectMapper;

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public OrderService(AxlClient axlClient,
                        AxlProperties axlProperties,
                        PaymentService paymentService,
                        WalletService walletService,
                        ObjectMapper objectMapper) {

        this.axlClient = axlClient;
        this.peerId = axlProperties.getPeerId();
        this.paymentService = paymentService;
        this.walletService = walletService;
        this.objectMapper = objectMapper;
    }

    public void handleEvent(OrderEvent event) {

        Order order = orders.computeIfAbsent(
                event.orderId(),
                id -> createOrder(event)
        );

        System.out.println("Order roles:");
        System.out.println("Buyer: " + order.getBuyerPeerId());
        System.out.println("Seller: " + order.getSellerPeerId());
        System.out.println("Me: " + peerId);

        System.out.println("Applying event type: " + event.type());
        order.apply(event);

        System.out.println("Order updated: " + order.getState());

        switch (event.type()) {

            case SERVICE_REQUEST -> {
                if (isSeller(order)) {
                    sendOrderAccepted(order);
                }
            }

            case ORDER_ACCEPTED -> {
                System.out.println("Order accepted triggered");
                if (isBuyer(order)) {
                    System.out.println("Accepted the order, proceeding to payment phase");
                    requestPayment(order);
                }
            }

            case PAYMENT_CONFIRMED -> {
                if (isSeller(order)) {
                    sendOrderCompleted(order);
                }
            }

            default -> {
            }
        }
    }

    private Order createOrder(OrderEvent event) {

        return switch (event.type()) {

            case SERVICE_REQUEST ->
                    new Order(
                            event.orderId(),
                            event.fromPeerId(), // buyer
                            event.toPeerId()    // seller
                    );

            default ->
                    new Order(
                            event.orderId(),
                            event.toPeerId(),   // buyer
                            event.fromPeerId()  // seller
                    );
        };
    }

    private void sendOrderAccepted(Order order) {

        try {
            OrderAcceptedPayload payload = new OrderAcceptedPayload(
                    "Accepted, preparing your order",
                    walletService.getAddress(),
                    "0.001"
            );

            sendMessage(
                    order.getBuyerPeerId(),
                    "ORDER_ACCEPTED",
                    order.getId(),
                    payload
            );

            System.out.println("[Seller] Sent ORDER_ACCEPTED " + order.getId());

        } catch (Exception e) {
            throw new RuntimeException("Failed to send ORDER_ACCEPTED", e);
        }
    }

    private void requestPayment(Order order) {

        Payment payment = new Payment(
                order.getId(),
                walletService.getAddress(),
                order.getSellerWalletAddress(),
                order.getPriceWei()
        );

        String txHash = paymentService.send(payment);

        System.out.println("[Buyer] Payment submitted: " + txHash);

        sendPaymentConfirmed(order, txHash);
    }

    private void sendPaymentConfirmed(Order order, String txHash) {

        try {
            PaymentConfirmedPayload payload = new PaymentConfirmedPayload(
                    "Payment sent",
                    txHash
            );

            sendMessage(
                    order.getSellerPeerId(),
                    "PAYMENT_CONFIRMED",
                    order.getId(),
                    payload
            );

            System.out.println("[Buyer] Sent PAYMENT_CONFIRMED " + order.getId());

        } catch (Exception e) {
            throw new RuntimeException("Failed to send PAYMENT_CONFIRMED", e);
        }
    }

    private void sendOrderCompleted(Order order) {

        try {
            OrderCompletedPayload payload = new OrderCompletedPayload(
                    "Order ready for pickup"
            );

            sendMessage(
                    order.getBuyerPeerId(),
                    "ORDER_COMPLETED",
                    order.getId(),
                    payload
            );

            System.out.println("[Seller] Sent ORDER_COMPLETED " + order.getId());

        } catch (Exception e) {
            throw new RuntimeException("Failed to send ORDER_COMPLETED", e);
        }
    }

    private void sendMessage(String toPeerId,
                             String type,
                             String orderId,
                             Object payload) throws Exception {

        String json = objectMapper.writeValueAsString(Map.of(
                "type", type,
                "orderId", orderId,
                "payload", payload
        ));

        axlClient.send(toPeerId, json);
    }

    private boolean isBuyer(Order order) {
        return peerId.equals(order.getBuyerPeerId());
    }

    private boolean isSeller(Order order) {
        return peerId.equals(order.getSellerPeerId());
    }
}