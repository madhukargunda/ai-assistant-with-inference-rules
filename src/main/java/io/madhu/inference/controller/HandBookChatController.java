/**
 * Author: Madhu
 * User:madhu
 * Date:7/9/25
 * Time:3:37 PM
 * Project: similarity-search-example
 */

package io.madhu.inference.controller;

import io.madhu.inference.util.UsageContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Profile("handbook")
public class HandBookChatController {

    @Autowired
    private ChatClient chatClient;

    @Value("classpath:/prompts/handbook-prompt_v3.st")
    private Resource handBookPrompt;

    @Value("classpath:/handbook.txt")
    private Resource handBook;

    @PostMapping("/api/chat")
    public String chat(@RequestBody String query) throws IOException {
        log.info("The Chat method invoked HandBookChatController..........");
        Prompt prompt = buildPrompt(query);
        log.info("Initiated chat prompt User Query {}", query);
        ChatClient.CallResponseSpec callResponseSpec = chatClient.prompt(buildPrompt(query))
                .advisors(new SimpleLoggerAdvisor())
                .call();
        log.info("Response Received from LLM is {}", callResponseSpec.content());
        UsageContext.set(callResponseSpec.chatResponse().getMetadata().getUsage());
        return callResponseSpec.content();
    }

    private Prompt buildPrompt(String query) throws IOException {
        PromptTemplate promptTemplate = new PromptTemplate(handBookPrompt);
        Message message = promptTemplate.createMessage(Map.of("input", query, "context",
                handBook.getContentAsString(Charset.defaultCharset())));
        return new Prompt(List.of(message));
    }
}
