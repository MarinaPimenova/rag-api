package com.epam.training.gen.ai.rag.embedding.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@RequiredArgsConstructor
public class VectorStoreConfig {
    private final PgvectorConfig pgvectorConfig;

    @Bean
    public VectorStore vectorStore(JdbcTemplate jdbcTemplate,
                                   EmbeddingModel embeddingModel) {
        return PgVectorStore.builder(jdbcTemplate, embeddingModel)
                .dimensions(pgvectorConfig.getDimensions())                    // Optional: defaults to model dimensions or 1536
                .distanceType(PgVectorStore.PgDistanceType.valueOf(pgvectorConfig.getDistanceType()))       // Optional: defaults to COSINE_DISTANCE
                .indexType(PgVectorStore.PgIndexType.valueOf(pgvectorConfig.getIndexType())/*HNSW*/)                     // Optional: defaults to HNSW
                .initializeSchema(pgvectorConfig.getInitializeSchema())              // Optional: defaults to false
                .schemaName(pgvectorConfig.getSchemaName())                // Optional: defaults to "public"
                .vectorTableName(pgvectorConfig.getTableName())     // Optional: defaults to "vector_store"
                .maxDocumentBatchSize(pgvectorConfig.getMaxDocumentBatchSize())         // Optional: defaults to 10000
                .build();
    }
}
