package com.epam.training.gen.ai.rag.generator.repository;

import com.epam.training.gen.ai.rag.generator.model.ChatHistory;
import com.epam.training.gen.ai.rag.generator.model.ChatHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatHistoryRepository extends JpaRepository<ChatHistory, ChatHistoryId> {

    List<ChatHistory> findAllBySessionIdOrderByTimestampAsc(UUID sessionId);
}

