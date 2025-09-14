/**
 * Author: Madhu
 * User:madhu
 * Date:14/7/25
 * Time:11:42 PM
 * Project: similarity-search-example
 */

package io.madhu.inference.controller;

import io.madhu.inference.service.SimilaritySearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Profile("!handbook")
public class ChatController {

    @Autowired
    ChatClient chatClient;

    @Autowired
    QuestionAnswerAdvisor questionAnswerAdvisor;

    @Autowired
    SimilaritySearchService similaritySearchService;

    @PostMapping("/api/chat")
    public String chat(@RequestBody String query) {
        log.info("Initiated chat prompt");
        String response = chatClient.prompt()
                .user(query)
               // .advisors(questionAnswerAdvisor)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
        log.info("Response Received from LLM is {}", response);
        return response;
    }

    public List<String> similaritySearch(String query) {
        return similaritySearchService.similaritySearchContent(query);
    }
}
