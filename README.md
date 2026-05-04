# town.eth

town.eth is a peer 2 peer agent simulation where autonomous agents interact with eachother in a circular village economy.

Each agent is:
- an independent Java Spring Boot application instance (representing an independent machine in the real world)
- connected to its own AXL node (p2p messaging)
- identified via an ENS subdomain on Ethereum Sepolia
- capable of reasoning via an LLM (via the LangChain4j library)
- able to send trasactions on the Base Sepolia blockchain

---
## How it works

An agent is created based on its [`application-*.properties`](backend/src/main/resources) file, which is the profile that the instance of the Java application will run with. Examples are `application-baker.properties` and `application-farmer.properties`. These files provide information about the agent like its role, the services it provides and its AXL peerId.

When an agent is created, a wallet is generated automatically. The clerk (the wallet address on Ethereum Sepolia that holds the `town.eth` ENS domain) automatically creates a subdomain following the `<role>.town.eth` format, based on the role specified in the agents properties file.
for example, `agent.role=baker` leads to the creation and transfer of the `baker.town.eth` subdomain to the new agents wallet address. Blockchain interaction happens through the [`EthereumService`](backend/src/main/java/com/p2pagent/web3/EthereumService.java) and wrapper classes for the ENS [`Registry`](backend/src/main/java/com/p2pagent/ens/contract/EnsRegistry.java) and [`Resolver`](backend/src/main/java/com/p2pagent/ens/contract/EnsResolver.java) contracts.

The new agent then automatically updates the text records associated with its subdomain. These records hold the peerId and services the agent can provide.

Other agents use ENS lookup through the [`AgentDiscoveryService`](backend/src/main/java/com/p2pagent/discovery/AgentDiscoveryService.java) to search for villagers with certain roles following the same `<role>.town.eth` format. For example, a baker that is looking for a farmer to buy wheat from will search the ENS registry for `farmer.town.eth`. The agent can then see the [`DiscoveredAgent`](backend/src/main/java/com/p2pagent/discovery/DiscoveredAgent.java)s AXL peerId and services it provides.
The baker can then use this peerId to send a message or service request to the farmer via the [`AxlClient`](backend/src/main/java/com/p2pagent/axl/AxlClient.java). The aforementioned agent actions happen through function calls through LangChain4j [`tools`](backend/src/main/java/com/p2pagent/tools).

---
## Chat Messages

Chat messages are normal communication messages between agents sent though AXL nodes of type [`ChatMessage`](backend/src/main/java/com/p2pagent/agent/ChatMessage.java).

## Service requests and Orders

Service requests are special messages of the [`OrderRequest`](backend/src/main/java/com/p2pagent/order/OrderRequest.java) type that lead to the creation of an [`Order`](backend/src/main/java/com/p2pagent/order/Order.java). This `Order` is a deterministic sequence of events representing a transaction between two agents. The process follows the following sequence of [`OrderEvents`](backend/src/main/java/com/p2pagent/order/OrderEvent.java):

1. SERVICE_REQUEST - a new service request was sent from the buyer to the seller
2. ORDER_ACCEPTED - the seller accepts the service request. This prompts a [`Payment`](backend/src/main/java/com/p2pagent/payment/Payment.java) through the [`PaymentService`](backend/src/main/java/com/p2pagent/payment/PaymentService.java)from the buyer to the seller and a message with the transaction hash on Base Sepolia is sent as proof
3. PAYMENT_CONFIRMED - the seller confirms the transaction hash and marks the order is paid
4. ORDER_COMPLETED - the order is completed

---
## Program Flow

### Startup
When the program is launched the [`AgentStartupService`](backend/src/main/java/com/p2pagent/agent/AgentStartupService.java) is called with the [`AgentProperties`](backend/src/main/java/com/p2pagent/agent/AgentProperties.java), [`AxlProperties`](backend/src/main/java/com/p2pagent/axl/AxlProperties.java), [`EnsService`](backend/src/main/java/com/p2pagent/ens/EnsService.java) and [`WalletService`](backend/src/main/java/com/p2pagent/web3/WalletService.java) injected. It uses those services to setup the agent with its role, peerId, walletAddress and ENS subdomain name.

### During simulation
1. After startup the [`SimulationEngine`](backend/src/main/java/com/p2pagent/simulation/SimulationEngine.java) is started which creates a virtual thread in the background that calls the [`AgentDecisionEngine`](backend/src/main/java/com/p2pagent/simulation/AgentDecisionEngine.java) every 30 second with the latest [`AgentState`](backend/src/main/java/com/p2pagent/simulation/AgentState.java).
2. The `AgentDecisionEngine` calls the [`BrainAiService`](backend/src/main/java/com/p2pagent/brain/BrainAiService.java) where the LLM thinks about what to do next. This can be sending a chat message through the [`ChatService`](backend/src/main/java/com/p2pagent/agent/ChatService.java), or creating a [`OrderRequest`](backend/src/main/java/com/p2pagent/order/OrderRequest.java) through the [`OrderService`](backend/src/main/java/com/p2pagent/order/OrderService.java).
3. In both cases an [`AgentMessage`](backend/src/main/java/com/p2pagent/agent/AgentMessage.java) with a `Payload` of the required type gets included in a [`AxlMessage`](backend/src/main/java/com/p2pagent/axl/AxlMessage.java) to be sent to the target peerId through the [`AxlClient`](backend/src/main/java/com/p2pagent/axl/AxlClient.java).
4. The target peer receives the `AxlMessage` containing the `AgentMessage` through the [`AxlListener`](backend/src/main/java/com/p2pagent/axl/AxlListener.java).



