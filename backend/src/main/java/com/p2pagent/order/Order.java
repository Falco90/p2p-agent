package com.p2pagent.order;

import com.p2pagent.order.payload.OrderAcceptedPayload;
import com.p2pagent.order.payload.ServiceRequestPayload;
import com.p2pagent.order.payload.PaymentConfirmedPayload;

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

    private BigDecimal priceEth;
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

    public void apply(OrderEvent event) {

        switch (event.type()) {

            case SERVICE_REQUEST -> {
                ServiceRequestPayload p = (ServiceRequestPayload) event.payload();
                this.item = p.item();
                this.quantity = p.quantity();
                setState(OrderState.QUOTED);
            }

            case ORDER_ACCEPTED -> {
                OrderAcceptedPayload p = (OrderAcceptedPayload) event.payload();
                this.sellerWalletAddress = p.paymentAddress();
                this.priceEth = new BigDecimal(p.priceEth());
                setState(OrderState.ACCEPTED);
            }

            case PAYMENT_CONFIRMED -> {
                PaymentConfirmedPayload p = (PaymentConfirmedPayload) event.payload();
                this.txHash = p.txHash();
                setState(OrderState.PAID);
            }

            case ORDER_COMPLETED -> setState(OrderState.COMPLETED);

            default -> {}
        }
    }

    private void setState(OrderState newState) {
        this.state = newState;
        this.updatedAt = Instant.now();
    }

    public BigInteger getPriceWei() {
        return priceEth == null
                ? BigInteger.ZERO
                : priceEth.movePointRight(18).toBigInteger();
    }

    public String getId() { return id; }
    public String getBuyerPeerId() { return buyerPeerId; }
    public String getSellerPeerId() { return sellerPeerId; }
    public OrderState getState() { return state; }
    public String getItem() { return item; }
    public int getQuantity() { return quantity; }
    public BigDecimal getPriceEth() { return priceEth; }
    public String getSellerWalletAddress() { return sellerWalletAddress; }
    public String getTxHash() { return txHash; }

    public enum OrderState {
        NEW,
        QUOTED,
        ACCEPTED,
        PAID,
        COMPLETED,
        CANCELLED
    }
}