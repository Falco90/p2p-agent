package com.p2pagent.payment;

import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

public class Payment {

    private final String id;
    private final String orderId;

    private final String fromAddress;
    private final String toAddress;

    private final BigInteger amountWei;

    private PaymentStatus status;

    private String txHash;

    private final Instant createdAt;
    private Instant updatedAt;

    public Payment(String orderId,
                   String fromAddress,
                   String toAddress,
                   BigInteger amountWei) {

        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;

        this.fromAddress = fromAddress;
        this.toAddress = toAddress;

        this.amountWei = amountWei;

        this.status = PaymentStatus.REQUESTED;

        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void markSubmitted(String txHash) {
        this.txHash = txHash;
        this.status = PaymentStatus.SUBMITTED;
        touch();
    }

    public void markConfirmed() {
        this.status = PaymentStatus.CONFIRMED;
        touch();
    }

    public void markFailed() {
        this.status = PaymentStatus.FAILED;
        touch();
    }

    public boolean isPending() {
        return status == PaymentStatus.REQUESTED || status == PaymentStatus.SUBMITTED;
    }

    public boolean isConfirmed() {
        return status == PaymentStatus.CONFIRMED;
    }

    private void touch() {
        this.updatedAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public BigInteger getAmountWei() {
        return amountWei;
    }

    public PaymentStatus getStatus() {
        return status;
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

    public enum PaymentStatus {
        REQUESTED,
        SUBMITTED,
        CONFIRMED,
        FAILED
    }
}