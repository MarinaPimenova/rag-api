package com.epam.training.gen.ai.rag.embedding.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.ai.mistralai")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MistralAiConfig {
    private String apiKey;
}
