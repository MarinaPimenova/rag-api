package com.epam.training.gen.ai.rag.generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistoryId implements Serializable {
    private UUID sessionId;
    private LocalDateTime timestamp;
}

