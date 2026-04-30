package com.p2pagent.agent;

public class AxlMessage {

    private final String body;
    private final String fromPeerId;

    public AxlMessage(String body, String fromPeerId) {
        this.body = body;
        this.fromPeerId = fromPeerId;
    }

    public String getBody() {
        return body;
    }

    public String getFromPeerId() {
        return fromPeerId;
    }
}
