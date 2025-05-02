package com.epam.training.gen.ai.rag.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.ai.openai")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIConfig {

    private String apiKey;

    private String baseUrl;
}
