package com.p2pagent.agent;

import com.p2pagent.axl.AxlProperties;
import com.p2pagent.order.OrderEvent;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    private final String localPeerId;

    public EventMapper(AxlProperties properties) {
        this.localPeerId = properties.getPeerId();
    }

    public <T> OrderEvent<T> toEvent(AgentMessage<T> msg) {
        return OrderEvent.fromAgentMessage(msg, localPeerId);
    }
}