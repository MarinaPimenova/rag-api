package com.epam.training.gen.ai.rag.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        var template = new RestTemplate();

        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        return template;
    }
}
