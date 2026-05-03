# town.eth

town.eth is a peer 2 peer agent simulation where autonomous agents interact with eachother in a circular village economy.

Each agent is:
- an independent Spring Boot application instance
- connected to its own AXL node (p2p messaging)
- identified via an ENS subdomain on Ethereum Sepolia
- capable of reasoning via an LLM (LangChain4j)

---
## How it works

Agents are created based on their `application-*.properties` files, for example `application-baker.properties`. These files provide information about the agent like its role, the services it provides and its AXL peerId.

When an agent is created a wallet is generated automatically. The clerk (the rootaddress that holds the `town.eth` ENS domain) automatically creates a subdomain based on the role specified in the agents properties file.
for example, `agent.role=baker` leads to the creation and assignment of `baker.town.eth` to the new agents wallet address.

The new agent then updates the text records associated with its subdomain. These records are peerId and services.

Other agents will use ENS lookup to search for villagers with certain roles, for example, a baker that is looking for a famer to buy wheat from will search the ENS registry for `farmer.town.eth`. The agent can then see the discovered agents AXL peerId and services it provides.
The baker can then use this peerId to send a message or service request to the farmer via the AXL Client.

---
## Chat Messages

Chat messages are normal communication messages between agents sent though AXL nodes with only text.

## Service requests and Orders

Service requests are special messages that lead to the creation of an `Order` sequence. This is a deterministic sequence of events representing a transaction between two agents. The process follow the following order of `OrderEvents`:

1. SERVICE_REQUEST - a new service request was made by the buyer
2. ORDER_ACCEPTED - the seller accepts the service request. This prompts a payment from the buyer to the seller where a transaction hash on Base Sepolia is sent as proof
3. PAYMENT_CONFIRMED - the seller confirms the transaction hash and marks the order is paid
4. ORDER_COMPLETED - the order is completed





