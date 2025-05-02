package com.epam.training.gen.ai.rag.controller;

import com.epam.training.gen.ai.rag.embedding.service.DataLoaderService;
import com.epam.training.gen.ai.rag.generator.service.GeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RAGController {
    private final DataLoaderService dataLoaderService;
    private final GeneratorService generatorService;

    @PostMapping("/ask")
    public ResponseEntity<Map<String, String>> ask(
            @RequestParam UUID sessionId,
            @RequestBody Map<String, String> request) {
        String question = request.get("question");
        String answer = generatorService.generate(sessionId, question);
        return ResponseEntity.ok(Map.of("answer", answer));
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadKnowledge(
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(dataLoaderService.uploadKnowledge(file));
    }

}
