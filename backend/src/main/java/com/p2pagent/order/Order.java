package com.p2pagent.order;

import java.time.Instant;

public class Order {

    private final String id;
    private final String buyerPeerId;
    private final String sellerPeerId;

    private OrderState state;

    private String item;
    private int quantity;
    private double price;

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

    public void apply(OrderEvent event) {

        switch (event.type()) {

            case SERVICE_REQUEST -> handleServiceRequest(event);

            case PAYMENT_SENT -> handlePayment(event);

            case ORDER_ACCEPTED -> setState(OrderState.ACCEPTED);

            case PAYMENT_CONFIRMED -> setState(OrderState.PAID);

            case ORDER_COMPLETED -> setState(OrderState.COMPLETED);

            default -> {
            }
        }
    }

    private void handleServiceRequest(OrderEvent event) {
        this.item = event.payload();
        setState(OrderState.QUOTED);
    }

    private void handlePayment(OrderEvent event) {
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

    public double getPrice() {
        return price;
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