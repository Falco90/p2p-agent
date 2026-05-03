package com.p2pagent.agent.context;

import org.springframework.stereotype.Component;

/**
 * Holds runtime identity of the currently executing agent.
 *
 * This is used by tools to know "who is acting".
 */
@Component
public class AgentContext {

    private String peerId;
    private String role;
    private String walletAddress;

    public String getPeerId() {
        return peerId;
    }

    public String getRole() {
        return role;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setPeerId(String peerId) {
        this.peerId = peerId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public void clear() {
        this.peerId = null;
        this.role = null;
        this.walletAddress = null;
    }
}