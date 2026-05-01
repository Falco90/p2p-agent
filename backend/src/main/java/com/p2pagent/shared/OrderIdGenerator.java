package com.p2pagent.shared;

import com.p2pagent.axl.AxlProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderIdGenerator {

    private final AxlProperties axlProperties;

    public OrderIdGenerator(AxlProperties axlProperties) {
        this.axlProperties = axlProperties;
    }

    public String generate() {
        String peerId = axlProperties.getPeerId();
        return "order-" + peerId.substring(0, 6) + "-" + UUID.randomUUID();
    }
}
