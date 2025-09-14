/**
 * Author: Madhu
 * User:madhu
 * Date:7/9/25
 * Time:3:29 PM
 * Project: similarity-search-example
 */

package io.madhu.inference.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;

@Service
@Slf4j
@Profile("handbook")
public class HandBookDocumentLoadService {

    @Value("classpath:/handbook.txt")
    private Resource handBook;

    @Autowired
    SimpleVectorStore simpleVectorStore;

   // @PostConstruct
    public void init() throws IOException {
        String contentAsString = handBook.getContentAsString(Charset.defaultCharset());
        log.info("Initializing the vectorStore of the HandBook  {} ", contentAsString);
       // simpleVectorStore.accept(List.of(new Document(contentAsString)));
    }
}
