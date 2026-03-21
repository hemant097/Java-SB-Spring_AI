package com.example.project.springai.service;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.multipdf.PDFCloneUtility;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RagService {

    private final ChatClient chatClient;
    private final EmbeddingModel embeddingModel;
    private final VectorStore vectorStore;

    @Value("classpath:faq.pdf")
    Resource pdfFile;

    public String askAI(String prompt){

        String template = """
                You are an AI assistant helping a software developer.
                
                Rule:
                - Use ONLY the information provided in the context
                - You MAY rephrase, summarize, and explain in natural language
                - Do NOT introduce new concepts or facts
                - If multiple context sections are relevant, combine them into a single explanation
                - If the answer is not present, say "I am not aware about this, as of now"
                
                Context:
                {context}
                
                Answer in a friendly, conversational tone.
                """;

        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
                            .query(prompt)
                            .topK(4)
                            .similarityThreshold(0.5)
            //                .filterExpression("genre == 'ai' or genre == 'tech' ") // restricting which documents are even considered before or along with vector search.
                                    .filterExpression(" file_name=='faq.pdf' ")
                            .build());

        //Converting documents into string
        String context = documents.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        PromptTemplate promptTemplate = new PromptTemplate(template);

        String systemPrompt = promptTemplate.render(Map.of("context",context));

        return chatClient.prompt()
                .system(systemPrompt)
                .user(prompt)
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .content();
    }

    public void ingestPdfToVectorStore(){
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(pdfFile);
        //breaking pdf file into individual pages
        List<Document> pages = pdfReader.get();

        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(200)
                .build();

        //breaking each pages into multiple chunk with chunkSize defined above
        List<Document> chunks = splitter.apply(pages);
        vectorStore.add(chunks);
    }

}
