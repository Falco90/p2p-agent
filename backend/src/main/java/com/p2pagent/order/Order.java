package com.p2pagent.order;

import com.p2pagent.order.payload.*;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

public class Order {

    private final String id;
    private final String buyerPeerId;
    private final String sellerPeerId;

    private OrderState state;

    private String item;
    private int quantity;
    private BigInteger priceWei;
    private String sellerWalletAddress;
    private String txHash;

    private final Instant createdAt;
    private Instant updatedAt;

    public Order(String id, String buyerPeerId, String sellerPeerId) {
        this.id = id;
        this.buyerPeerId = buyerPeerId;
        this.sellerPeerId = sellerPeerId;
        this.state = OrderState.NEW;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void apply(OrderEvent<?> event) {

        switch (event.type()) {

            case SERVICE_REQUEST -> handleServiceRequest(
                    (ServiceRequestPayload) event.payload()
            );

            case ORDER_ACCEPTED -> handleOrderAccepted(
                    (OrderAcceptedPayload) event.payload()
            );

            case PAYMENT_CONFIRMED -> handlePaymentConfirmed(
                    (PaymentConfirmedPayload) event.payload()
            );

            case ORDER_COMPLETED -> setState(OrderState.COMPLETED);

            default -> {}
        }
    }

    private void handleServiceRequest(ServiceRequestPayload payload) {
        this.item = payload.item();
        this.quantity = payload.quantity();
        setState(OrderState.QUOTED);
    }

    private void handleOrderAccepted(OrderAcceptedPayload payload) {
        this.sellerWalletAddress = payload.sellerWalletAddress();
        this.priceWei = Convert.toWei(
                payload.priceEth(),
                Convert.Unit.ETHER
        ).toBigInteger();

        setState(OrderState.ACCEPTED);
    }

    private void handlePaymentConfirmed(PaymentConfirmedPayload payload) {
        this.txHash = payload.txHash();
        setState(OrderState.PAID);
    }

    private void setState(OrderState newState) {
        this.state = newState;
        this.updatedAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getBuyerPeerId() {
        return buyerPeerId;
    }

    public String getSellerPeerId() {
        return sellerPeerId;
    }

    public OrderState getState() {
        return state;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigInteger getPriceWei() {
        return priceWei;
    }

    public String getSellerWalletAddress() {
        return sellerWalletAddress;
    }

    public String getTxHash() {
        return txHash;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public enum OrderState {
        NEW,
        QUOTED,
        ACCEPTED,
        PAID,
        COMPLETED,
        CANCELLED
    }
}