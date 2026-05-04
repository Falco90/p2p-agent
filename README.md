# town.eth

town.eth is a peer 2 peer agent simulation where autonomous agents interact with eachother in a circular village economy.

Each agent is:
- an independent Spring Boot application instance (representing an independent machine in the real world)
- connected to its own AXL node (p2p messaging)
- identified via an ENS subdomain on Ethereum Sepolia
- capable of reasoning via an LLM (via the LangChain4j library)

---
## How it works

An agent is created based on its `application-*.properties`backend/src/main/java/com/p2pagent/resources file, which is the profile that the instance of the Java application will run with. Examples are [`application-baker.properties`](backend/src/main/java/com/p2pagent/resources/application-baker.properties) and `application-farmer.properties`. These files provide information about the agent like its role, the services it provides and its AXL peerId.

When an agent is created, a wallet is generated automatically. The clerk (the wallet address on Ethereum Sepolia that holds the `town.eth` ENS domain) automatically creates a subdomain following the `<role>.town.eth` format, based on the role specified in the agents properties file.
for example, `agent.role=baker` leads to the creation and transfer of the `baker.town.eth` subdomain to the new agents wallet address. Blockchain interaction happens through the Web3j library and wrapper classes for the ENS Registry and Resolver contracts.

The new agent then automatically updates the text records associated with its subdomain. These records hold the peerId and services the agent can provide.

Other agents use ENS lookup to search for villagers with certain roles following the same `<role>.town.eth` format. For example, a baker that is looking for a farmer to buy wheat from will search the ENS registry for `farmer.town.eth`. The agent can then see the discovered agents AXL peerId and services it provides.
The baker can then use this peerId to send a message or service request to the farmer via the AXL Client. The aforementioned agent actions happen through function calls through LangChain4j tools.

---
## Chat Messages

Chat messages are normal communication messages between agents sent though AXL nodes with only text.

## Service requests and Orders

Service requests are special messages that lead to the creation of an `Order` sequence. This is a deterministic sequence of events representing a transaction between two agents. The process follow the following sequence of `OrderEvents`:

1. SERVICE_REQUEST - a new service request was made by the buyer
2. ORDER_ACCEPTED - the seller accepts the service request. This prompts a payment from the buyer to the seller and a message with the transaction hash on Base Sepolia is sent as proof
3. PAYMENT_CONFIRMED - the seller confirms the transaction hash and marks the order is paid
4. ORDER_COMPLETED - the order is completed

---
## Program Flow

### Startup
When the program is launched the `AgentStartupService` is called with the `AgentProperties`, `AxlProperties`, `EnsService` and `WalletService` injected. It uses those services to setup the agent with its role, peerId, walletAddress and ENS subdomain name.

### During simulation
1. After startup the `SimulationEngine` is started which creates a virtual thread in the background that calls the `AgentDecisionEnginge` every 30 second with the latest `AgentState`.
2. The `AgentDecisionEngine` calls the `BrainAiService` where the LLM thinks about what to do next. This can be sending a chat message through the `ChatService`, or creating a `ServiceRequest` through the `OrderService`.
3. In both cases an `AgentMessage` with a `Payload` of the required type gets included in a `AxlMessage` to be sent to the target peerId through the `AxlClient`.
4. The target peer receives the `AxlMessage` containing the `AgentMessage` through the `AxlListener`.



