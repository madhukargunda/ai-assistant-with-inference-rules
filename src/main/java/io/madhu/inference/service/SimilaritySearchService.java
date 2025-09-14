/**
 * Author: Madhu
 * User:madhu
 * Date:14/7/25
 * Time:10:29 PM
 * Project: similarity-search-example
 */

package io.madhu.inference.service;


import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimilaritySearchService {

    @Autowired
    private SimpleVectorStore simpleVectorStore;

    public List<String> similaritySearchContent(String query) {
        SearchRequest searchRequest = SearchRequest.builder().query(query).topK(3).similarityThreshold(0.9).build();
        List<Document> similaritySearchResults = simpleVectorStore.similaritySearch(searchRequest);
        return similaritySearchResults
                .stream()
                .map(d -> d.getFormattedContent())
                .collect(Collectors.toList());
    }
}
