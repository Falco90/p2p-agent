package com.p2pagent.tools;

import com.p2pagent.discovery.AgentDiscoveryService;
import com.p2pagent.discovery.DiscoveredAgent;
import com.p2pagent.order.OrderService;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class AgentTools {

    private final OrderService orderService;
    private final AgentDiscoveryService discovery;
    private final CommunicationTools communicationTools;

    public AgentTools(OrderService orderService,
                      AgentDiscoveryService discovery, CommunicationTools communicationTools) {
        this.orderService = orderService;
        this.discovery = discovery;
        this.communicationTools = communicationTools;
    }

    @Tool("""
            Send a direct chat message to another agent in the simulation.
            
            Rules:
            - This is the ONLY valid way to communicate with other agents.
            - Do NOT include reasoning, narration, or explanation in the message.
            - The message should be short, direct, and actionable.
            - This is a real system action, not conversational text.
            
            Parameters:
            - toPeerId: the target agent's unique peer ID
            - message: the content of the message to deliver
            
            Outcome:
            - The message will be delivered immediately to the recipient's ChatService inbox.
            """)
    public void sendChatMessageToAgent(String toPeerId, String message) {
        communicationTools.sendChatMessage(toPeerId, message);
    }

    @Tool("""
            Request a service from another agent in the economy.
            
            This sends a service request to the agent identified by role.
            
            Rules:
            - targetRole must match a valid agent role (e.g. baker, farmer, guard)
            - service must be something that agent provides (e.g. wheat, bread, protection)
            - quantity defines how much of the service is requested
            
            This does NOT execute immediately. It creates a service request in the simulation.
            
            Example:
            requestService("farmer", "wheat", 3)
            """)
    public void requestServiceFromAgent(String targetRole, String service, int quantity) {
        orderService.requestService(targetRole, service, quantity);
    }

    @Tool("""
            Resolve an agent by role.
            
            Each role maps 1-to-1 to an ENS name:
            
            role.town.eth
            
            Example:
            - baker → baker.town.eth
            - farmer → farmer.town.eth
            
            Returns full agent identity including:
            - ENS name
            - peerId
            - role
            - wallet address
            - services
            """)
    public DiscoveredAgent resolveByRole(String role) {
        return discovery.findByRole(role);
    }

}