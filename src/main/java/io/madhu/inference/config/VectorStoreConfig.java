/**
 * Author: Madhu
 * User:madhu
 * Date:11/7/25
 * Time:4:35 PM
 * Project: similarity-search-example
 */

package io.madhu.inference.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorStoreConfig {

    @Autowired
    EmbeddingModel embeddingModel;

    @Bean
    SimpleVectorStore vectorStore(EmbeddingModel embeddingModel) {
        return SimpleVectorStore
                .builder(embeddingModel)
                .build();
    }
}
