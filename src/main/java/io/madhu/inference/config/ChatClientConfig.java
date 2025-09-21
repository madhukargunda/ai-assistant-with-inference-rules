/**
 * Author: Madhu
 * User:madhu
 * Date:14/7/25
 * Time:11:28 PM
 * Project: similarity-search-example
 */

package io.madhu.inference.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Autowired
    ChatMemory chatMemory;

    @Autowired
    SimpleVectorStore simpleVectorStore;

    @Bean
    public SimpleLoggerAdvisor simpleLoggerAdvisor() {
        return new SimpleLoggerAdvisor();
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClient, MessageWindowChatMemory messageWindowChatMemory) {
        return chatClient.defaultAdvisors(simpleLoggerAdvisor()).
                build();
    }

    @Bean
    public QuestionAnswerAdvisor questionAnswerAdvisor(@Autowired SimpleVectorStore simpleVectorStore) {
        return QuestionAnswerAdvisor.builder(simpleVectorStore)
                .searchRequest(
                        SearchRequest
                                .builder()
                                .topK(1)
                                .similarityThreshold(0.8)
                                .build()
                )
                .build();
    }
}
