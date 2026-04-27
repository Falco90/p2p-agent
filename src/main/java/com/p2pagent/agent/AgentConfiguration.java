package com.p2pagent.agent;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
public class AgentConfiguration {

    /**
     * This chat memory will be used by {@link Agent}
     */
    @Bean
    @Scope(SCOPE_PROTOTYPE)
    ChatMemory chatMemory() {
        return MessageWindowChatMemory.withMaxMessages(10);
    }

    /**
     * This listener will be injected into every {@link ChatModel} and {@link StreamingChatModel}
     * bean   found in the application context.
     * It will listen for {@link ChatModel} and
     * {@link Agent}}.
     */
    @Bean
    ChatModelListener chatModelListener() {
        return new MyChatModelListener();
    }
}
