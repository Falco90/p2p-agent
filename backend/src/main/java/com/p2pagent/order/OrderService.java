package com.p2pagent.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2pagent.axl.AxlClient;
import com.p2pagent.axl.AxlProperties;
import com.p2pagent.catalog.ProductCatalog;
import com.p2pagent.order.payload.*;
import com.p2pagent.payment.Payment;
import com.p2pagent.payment.PaymentService;
import com.p2pagent.pricing.PricingService;
import com.p2pagent.web3.WalletService;
import org.springframework.stereotype.Service;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private final AxlClient axlClient;
    private final String peerId;
    private final PaymentService paymentService;
    private final WalletService walletService;
    private final PricingService pricingService;
    private final ProductCatalog catalog;
    private final ObjectMapper objectMapper;

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public OrderService(AxlClient axlClient,
                        AxlProperties props,
                        PaymentService paymentService,
                        WalletService walletService,
                        PricingService pricingService,
                        ProductCatalog catalog,
                        ObjectMapper objectMapper) {

        this.axlClient = axlClient;
        this.peerId = props.getPeerId();
        this.paymentService = paymentService;
        this.walletService = walletService;
        this.pricingService = pricingService;
        this.catalog = catalog;
        this.objectMapper = objectMapper;
    }

    public void handleEvent(OrderEvent<?> event) {

        Order order = orders.computeIfAbsent(
                event.orderId(),
                id -> createOrder(event)
        );

        logRoles(order);

        System.out.println("Applying event: " + event.type());

        order.apply(event);

        System.out.println("Order state: " + order.getState());

        switch (event.type()) {

            case SERVICE_REQUEST -> handleServiceRequest(event, order);

            case ORDER_ACCEPTED -> handleOrderAccepted(order);

            case PAYMENT_CONFIRMED -> handlePaymentConfirmed(order);

            default -> {}
        }
    }

    private void handleServiceRequest(OrderEvent<?> event, Order order) {

        if (!isSeller(order)) return;

        ServiceRequestPayload payload = (ServiceRequestPayload) event.payload();

        if (!catalog.hasItem(payload.item())) {
            System.out.println("Item not available: " + payload.item());
            return;
        }

        BigInteger priceWei = pricingService.calculatePriceWei(
                payload.item(),
                payload.quantity()
        );

        // ⚠️ still needed (pricing is external logic)
        // acceptable for now
        try {
            String priceEth = Convert.fromWei(
                    new BigDecimal(priceWei),
                    Convert.Unit.ETHER
            ).toString();

            OrderAcceptedPayload response = new OrderAcceptedPayload(
                    "Accepted, preparing your order",
                    walletService.getAddress(),
                    priceEth
            );

            sendMessage(
                    order.getBuyerPeerId(),
                    "ORDER_ACCEPTED",
                    order.getId(),
                    response
            );

            System.out.println("[Seller] Sent ORDER_ACCEPTED");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleOrderAccepted(Order order) {

        if (!isBuyer(order)) return;

        System.out.println("Proceeding to payment...");

        Payment payment = new Payment(
                order.getId(),
                walletService.getAddress(),
                order.getSellerWalletAddress(),
                order.getPriceWei()
        );

        String txHash = paymentService.send(payment);

        sendPaymentConfirmed(order, txHash);
    }

    private void handlePaymentConfirmed(Order order) {

        if (!isSeller(order)) return;

        sendOrderCompleted(order);
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

        } catch (Exception e) {
            throw new RuntimeException(e);
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

        } catch (Exception e) {
            throw new RuntimeException(e);
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

    private Order createOrder(OrderEvent<?> event) {

        return switch (event.type()) {

            case SERVICE_REQUEST ->
                    new Order(
                            event.orderId(),
                            event.fromPeerId(),
                            event.toPeerId()
                    );

            default ->
                    new Order(
                            event.orderId(),
                            event.toPeerId(),
                            event.fromPeerId()
                    );
        };
    }

    private boolean isBuyer(Order order) {
        return peerId.equals(order.getBuyerPeerId());
    }

    private boolean isSeller(Order order) {
        return peerId.equals(order.getSellerPeerId());
    }

    private void logRoles(Order order) {
        System.out.println("Buyer: " + order.getBuyerPeerId());
        System.out.println("Seller: " + order.getSellerPeerId());
        System.out.println("Me: " + peerId);
    }
}