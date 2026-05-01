package com.p2pagent.agent;

import com.p2pagent.axl.AxlProperties;
import com.p2pagent.order.OrderEvent;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    private final AxlProperties axlProperties;

    public EventMapper(AxlProperties axlProperties) {
        this.axlProperties = axlProperties;
    }

    public OrderEvent toEvent(AgentMessage msg) {

        String localPeerId = axlProperties.getPeerId();

        return switch (msg.type()) {

            case SERVICE_REQUEST ->
                    OrderEvent.request(msg, localPeerId);

            case ORDER_ACCEPTED -> OrderEvent.accepted(msg, localPeerId);

            case PAYMENT_SENT ->
                    OrderEvent.payment(msg, localPeerId);

            case ORDER_COMPLETED -> OrderEvent.completed(msg, localPeerId);

            default -> throw new IllegalArgumentException(
                    "Unhandled message type: " + msg.type()
            );
        };
    }
}