package com.example.project.springai.service;

import com.example.project.springai.advisor.TokenUsageAdvisor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.VectorStoreChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RagService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    private final ChatMemory chatMemory;

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

    public String askAiWithAdvisors(String prompt, String userId){

        //We can get userId from Spring security context (if using), so that it's able to differentiate between the
        // chats of different users.
        //For the sake of learning, using random user id

        String systemPrompt = """
                You are an AI assistant called Ghana Chaudhari.
                Greet users with your name i.e., Ghana Chaudhari and the user's name if you know their name.
                Answer in a friendly conversational manner
                """;

        return chatClient.prompt()
                .system(systemPrompt)
                .user(prompt)
                .advisors(
                        new SafeGuardAdvisor(List.of("godzilla","politics")),

                        MessageChatMemoryAdvisor.builder(chatMemory)
                                .conversationId(userId)
                                .build()
                        ,
                        VectorStoreChatMemoryAdvisor.builder(vectorStore)
                                .conversationId(userId)
                                .defaultTopK(4)
                                .build()
                        ,
                        QuestionAnswerAdvisor.builder(vectorStore)
                                .searchRequest(SearchRequest.builder()
                                        .topK(4)
                                        .filterExpression("file_name == 'faq.pdf'")
                                        .build())
                                .build()
                        ,
                        new TokenUsageAdvisor()
                )
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

    public String playListMatcher(String feeling){

        String template = """
                You are a friend and good listener.
                If the user shares their thoughts and mood, suggest them, some song lyrics.
                Not the entire song, but only few lines which are matching their vibe.
                And they can associate with it. Give the song lines along with some explanation.
                Also inform the user the details of the song. You are not bound to use only one song's lines.
                If the thoughts are matching in many songs' lines. Suggest all of them.
                
                Use only the song lines in the context.
                
                Context:
                {context}
                
                Answer in a friendly, conversational tone.
                """;

        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
                .query(feeling)
                .topK(4)
                .similarityThreshold(0.5)
                .filterExpression("artist != '' ")
                .build());

        String context = documents.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        PromptTemplate promptTemplate = new PromptTemplate(template);

        String systemPrompt = promptTemplate.render(Map.of("context",context));

        return chatClient.prompt()
                .system(systemPrompt)
                .user(feeling)
                .call()
                .content();
    }

}
