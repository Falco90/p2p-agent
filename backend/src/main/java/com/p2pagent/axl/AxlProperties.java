package com.p2pagent.axl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AxlProperties {

    private final String peerId;

    public AxlProperties(@Value("${axl.peerId}") String peerId) {
        this.peerId = peerId;
    }

    public String getPeerId() {
        return peerId;
    }
}