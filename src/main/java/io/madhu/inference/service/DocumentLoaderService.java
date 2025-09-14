/**
 * Author: Madhu
 * User:madhu
 * Date:14/7/25
 * Time:11:23 PM
 * Project: similarity-search-example
 */

package io.madhu.inference.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentLoaderService {

    @Autowired
    public SimpleVectorStore simpleVectorStore;

    //@PostConstruct
    public void loadDocuments() {
        List<Document> documents = List.of(
                new Document("Spring Boot is an open-source Java-based framework used to create microservices."),
                new Document("Qdrant is a vector search engine optimized for fast similarity search."),
                new Document("OpenAI provides APIs for natural language processing and embedding generation.")
        );
        simpleVectorStore.accept(documents);
    }
}
