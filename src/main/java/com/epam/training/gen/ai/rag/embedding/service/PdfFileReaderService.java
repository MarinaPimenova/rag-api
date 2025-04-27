package com.epam.training.gen.ai.rag.embedding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfFileReaderService {
    private final VectorStore vectorStore;

    public void addResource(Resource resource) {
        ExtractedTextFormatter textFormatter = ExtractedTextFormatter.builder()
                .withNumberOfBottomTextLinesToDelete(3)
                .withNumberOfTopPagesToSkipBeforeDelete(1)
                .build();
        PdfDocumentReaderConfig pdfDocumentReaderConfig = PdfDocumentReaderConfig.builder()
                .withPageExtractedTextFormatter(textFormatter)
                .withPagesPerDocument(1)
                .build();

        PagePdfDocumentReader pagePdfDocumentReader =
                new PagePdfDocumentReader(resource, pdfDocumentReaderConfig);

        TokenTextSplitter textSplitter = new TokenTextSplitter();
        vectorStore.accept(textSplitter.apply(pagePdfDocumentReader.get()));
    }
}
