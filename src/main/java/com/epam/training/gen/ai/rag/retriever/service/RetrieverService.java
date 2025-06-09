package com.epam.training.gen.ai.rag.retriever.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetrieverService {
    private final VectorStore vectorStore;

    public String retrieveRelevantContext(String message) {

        try {
            List<Document> documents = vectorStore.similaritySearch(message); // java.lang.NullPointerException: Cannot invoke "java.util.List.stream()" because the return value of "org.springframework.ai.openai.api.OpenAiApi$EmbeddingList.data()" is null

            String collect =
                    documents.stream().map(Document::getText).collect(Collectors.joining(System.lineSeparator()));
            return String.join("\n", collect);
        } catch (Throwable ex) {
            log.error("Failed to get information from external resources. Caused by: {}", ex.getMessage());
            return "";
        }
    }
}
