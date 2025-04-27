package com.epam.training.gen.ai.rag.generator.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chat_history")
@IdClass(ChatHistoryId.class)
@Data
public class ChatHistory {

    @Id
    private UUID sessionId;

    @Id
    private LocalDateTime timestamp;

    private String role; // user or assistant

    @Column(columnDefinition = "TEXT")
    private String message;
}

