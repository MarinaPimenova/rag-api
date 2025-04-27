package com.epam.training.gen.ai.rag.retriever.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieverService {
    private final VectorStore vectorStore;

    public String retrieveRelevantContext(String message) {
        List<Document> documents = vectorStore.similaritySearch(message);
        String collect =
                documents.stream().map(Document::getText).collect(Collectors.joining(System.lineSeparator()));
        return String.join("\n", collect);
    }
}
