package com.epam.training.gen.ai.rag.generator.service;

import com.epam.training.gen.ai.rag.generator.model.ChatHistory;
import com.epam.training.gen.ai.rag.retriever.service.RetrieverService;
import lombok.RequiredArgsConstructor;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneratorService {
    private String template = """
            You're assisting with questions.
            Use the following context and chat history to answer the question but act as if you knew this information innately.
            If unsure, simply state that you don't know.
            History:
            {conversations}
            DOCUMENTS:
            {documents}
            """;

    private final RetrieverService retrieverService;
    private final ChatSessionService chatSessionService;

    private final ChatModel chatModel;

    public String generate(UUID sessionId, String question) {
        String context = retrieverService.retrieveRelevantContext(question);
        List<ChatHistory> chatHistory = chatSessionService.getChatHistory(sessionId);

        StringBuilder conversation = new StringBuilder();
        for (ChatHistory message : chatHistory) {
            conversation.append(message.getRole()).append(": ").append(message.getMessage()).append("\n");
        }

        Message createdMessage = new SystemPromptTemplate(template).createMessage(
                Map.of("documents", context,
                        "conversations", conversation));
        UserMessage userMessage = new UserMessage(question);
        Prompt prompt = new Prompt(List.of(createdMessage, userMessage));
        ChatResponse chatResponse = chatModel.call(prompt);

        chatSessionService.saveMessage(sessionId, "user", question);
        String answer = chatResponse.getResults().stream()
                .map(generation -> generation.getOutput().getText())
                .collect(Collectors.joining("/n"));
        chatSessionService.saveMessage(sessionId, "assistant", answer);

        return answer;
    }
}

