package com.epam.training.gen.ai.rag.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.model.ApiKey;
import org.springframework.ai.model.SimpleApiKey;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;

import org.springframework.web.reactive.function.client.WebClient;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@RequiredArgsConstructor
public class OpenAiApiConfig {
    private final AIConfig aiConfig;

    @Bean
    public OpenAiApi openAiApi(
            RestClient.Builder restClientBuilder,
            WebClient.Builder webClientBuilder,
            ResponseErrorHandler responseErrorHandler) {

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("cache-control", "no-cache");
        headers.add("Api-Key", aiConfig.getApiKey());
        ApiKey apiKey = new SimpleApiKey(aiConfig.getApiKey());
        return new OpenAiApi(aiConfig.getBaseUrl(), apiKey, headers,
                "/openai/deployments/gpt-4.1-mini-2025-04-14/chat/completions",
                "/openai/deployments/text-embedding-ada-002/embeddings",
                restClientBuilder, webClientBuilder, responseErrorHandler
        );
    }

    @Bean
    public ChatModel chatModel(OpenAiApi openAiApi) {
        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .build();
    }

    @Bean
    public OpenAiEmbeddingModel openAiEmbeddingModel(OpenAiApi openAiApi) {
        return new OpenAiEmbeddingModel(
                openAiApi,
                MetadataMode.EMBED,
                OpenAiEmbeddingOptions.builder()
                        .model("text-embedding-ada-002")
                        .user("user-6")
                        .build(),
                RetryUtils.DEFAULT_RETRY_TEMPLATE);
    }
}
