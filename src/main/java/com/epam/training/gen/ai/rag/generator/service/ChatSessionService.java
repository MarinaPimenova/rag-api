package com.epam.training.gen.ai.rag.generator.service;

import com.epam.training.gen.ai.rag.generator.model.ChatHistory;
import com.epam.training.gen.ai.rag.generator.repository.ChatHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ChatSessionService {

    private final ChatHistoryRepository repository;

    public ChatSessionService(ChatHistoryRepository repository) {
        this.repository = repository;
    }

    public void saveMessage(UUID sessionId, String role, String message) {
        ChatHistory chat = new ChatHistory();
        chat.setSessionId(sessionId);
        chat.setRole(role);
        chat.setMessage(message);
        chat.setTimestamp(LocalDateTime.now());
        repository.save(chat);
    }

    public List<ChatHistory> getChatHistory(UUID sessionId) {
        return repository.findAllBySessionIdOrderByTimestampAsc(sessionId);
    }
}

