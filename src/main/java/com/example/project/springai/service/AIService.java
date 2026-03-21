package com.example.project.springai.service;

import com.example.project.springai.dto.Joke;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AIService {

    private final ChatClient chatClient;
    private final EmbeddingModel embeddingModel;
    private final VectorStore vectorStore;


    public String getJokeOnTopic(String topic){

        String systemPrompt = """
                You are a sarcastic joker, you make poetic jokes in 2 lines.
                You also include politics in your jokes.
                Give a joke on the topic : {jokeTopic}
                """;

        PromptTemplate promptTemplate = new PromptTemplate(systemPrompt);

        String renderedText = promptTemplate.render(Map.of("jokeTopic",topic));

        var response =  chatClient.prompt()
//                .system("you are a sarcastic joker, give response in 1 line only")
                .user(renderedText)
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .entity(Joke.class) //used to convert into a custom class format
//                .content() //directly returns a String response
//                .chatClientResponse()
                ;

        return response
                .text()
//                .chatResponse().getResult().getOutput().getText()
                ;


    }

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
                        .topK(2)
                        .similarityThreshold(0.5)
                        .filterExpression("genre == 'ai' or genre == 'tech' ") // restricting which documents are even considered before or along with vector search.
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

    public float[] embed(String text){
        return embeddingModel.embed(text);
    }

    public void ingestDataToVectorStore(){
        List<Document> movies = returnMoviesDocumentList();
        vectorStore.add(movies);
        vectorStore.add(returnAIandTechDocumentsList());
    }

    public  List<Document> similaritySearch(String text){
//       return vectorStore.similaritySearch(text);
        return vectorStore.similaritySearch(SearchRequest.builder()
                        .query(text)
                        .topK(3) //returns top K most similar items, or K most similar vectors
                        .similarityThreshold(0.4) // only includes result that're above this similarity score
                .build());
    }


    List<Document> returnMoviesDocumentList(){
        return List.of(
                new Document(
                        "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                        Map.of("title", "Interstellar", "genre", "scifi", "year", 2014, "rating", 8.7, "director", "Christopher Nolan")
                ),
                new Document(
                        "A thief who steals corporate secrets through dream-sharing technology is given the task of planting an idea.",
                        Map.of("title", "Inception", "genre", "scifi", "year", 2010, "rating", 8.8, "director", "Christopher Nolan")
                ),
                new Document(
                        "A young wizard enrolls in a school of wizardry and discovers his magical heritage while battling dark forces.",
                        Map.of("title", "Harry Potter and the Sorcerer's Stone", "genre", "fantasy", "year", 2001, "rating", 7.6, "director", "Chris Columbus")
                ),
                new Document(
                        "A hobbit embarks on an unexpected journey with a group of dwarves to reclaim their homeland from a dragon.",
                        Map.of("title", "The Hobbit", "genre", "fantasy", "year", 2012, "rating", 7.8, "director", "Peter Jackson")
                ),
                new Document(
                        "A boxer from Philadelphia rises through the ranks and gets a shot at the world heavyweight championship.",
                        Map.of("title", "Rocky", "genre", "drama", "year", 1976, "rating", 8.1, "director", "John G. Avildsen")
                ),
                new Document(
                        "Two imprisoned men bond over years, finding solace and eventual redemption through acts of decency.",
                        Map.of("title", "The Shawshank Redemption", "genre", "drama", "year", 1994, "rating", 9.3, "director", "Frank Darabont")
                ),
                new Document(
                        "A computer hacker learns the world he lives in is a simulated reality and joins a rebellion against its machine controllers.",
                        Map.of("title", "The Matrix", "genre", "scifi", "year", 1999, "rating", 8.7, "director", "The Wachowskis")
                ),
                new Document(
                        "A young lion prince flees his kingdom after the murder of his father and learns to take his place in the circle of life.",
                        Map.of("title", "The Lion King", "genre", "animation", "year", 1994, "rating", 8.5, "director", "Roger Allers")
                )
        );
    }

    List<Document> returnAIandTechDocumentsList(){
        return List.of(
                new Document(
                        "AI systems use models trained on data to perform tasks like classification, prediction, and language understanding.",
                        Map.of("title", "AI Basics", "genre", "ai", "year", 2020, "rating", 8.2, "topic", "introduction")
                ),
                new Document(
                        "Embeddings convert text into dense numerical vectors so similar words and sentences can be compared using distance metrics.",
                        Map.of("title", "Understanding Embeddings", "genre", "ai", "year", 2021, "rating", 8.5, "topic", "embedding")
                ),
                new Document(
                        "Vector databases store high-dimensional embeddings and enable fast similarity search using indexes like HNSW or IVF.",
                        Map.of("title", "Vector Store Guide", "genre", "ai", "year", 2022, "rating", 8.4, "topic", "vectorstore")
                ),
                new Document(
                        "Retrieval-Augmented Generation combines document retrieval with language models to answer questions using external knowledge.",
                        Map.of("title", "Intro to RAG", "genre", "ai", "year", 2023, "rating", 8.8, "topic", "rag")
                ),
                new Document(
                        "Semantic search compares query and document embeddings to find meaning-based matches instead of exact keyword overlap.",
                        Map.of("title", "Semantic Search", "genre", "ai", "year", 2022, "rating", 8.3, "topic", "embedding")
                ),
                new Document(
                        "Vector stores scale by sharding embeddings and using approximate nearest neighbor search for low-latency retrieval.",
                        Map.of("title", "Scaling Vector Databases", "genre", "ai", "year", 2023, "rating", 8.6, "topic", "vectorstore")
                ),
                new Document(
                        "RAG pipelines retrieve relevant documents, inject them as context, and generate grounded responses using LLMs.",
                        Map.of("title", "RAG Pipelines", "genre", "ai", "year", 2024, "rating", 9.0, "topic", "rag")
                ),
                new Document(
                        "Machine learning models learn patterns from labeled or unlabeled data to make predictions or decisions.",
                        Map.of("title", "Machine Learning 101", "genre", "ai", "year", 2019, "rating", 8.1, "topic", "ml")
                ),
                new Document(
                        "Embeddings are used in recommendation systems to group similar users or items based on vector similarity.",
                        Map.of("title", "Embedding Applications", "genre", "ai", "year", 2021, "rating", 8.4, "topic", "embedding")
                ),
                new Document(
                        "Hybrid search combines keyword-based retrieval with vector similarity to improve recall and ranking quality.",
                        Map.of("title", "Hybrid Search Systems", "genre", "ai", "year", 2023, "rating", 8.7, "topic", "search")
                ),
                new Document(
                        "APIs define how services communicate by exposing endpoints for requests and responses over HTTP.",
                        Map.of("title", "API Fundamentals", "genre", "tech", "year", 2018, "rating", 7.9, "topic", "api")
                ),
                new Document(
                        "Distributed systems coordinate multiple nodes to handle data storage, computation, and fault tolerance.",
                        Map.of("title", "Distributed Systems", "genre", "tech", "year", 2020, "rating", 8.3, "topic", "systems")
                ),
                new Document(
                        "Cloud platforms provide scalable compute, storage, and networking resources on demand over the internet.",
                        Map.of("title", "Cloud Computing Basics", "genre", "tech", "year", 2019, "rating", 8.0, "topic", "cloud")
                ),
                new Document(
                        "Database indexing improves query speed by organizing data structures like B-trees or inverted indexes.",
                        Map.of("title", "Database Indexing", "genre", "tech", "year", 2021, "rating", 8.2, "topic", "database")
                ),
                new Document(
                        "Caching stores frequently accessed data in memory to reduce latency and improve application performance.",
                        Map.of("title", "Caching Techniques", "genre", "tech", "year", 2022, "rating", 8.4, "topic", "performance")
                ),
                new Document(
                        "Search engines crawl, index, and rank documents using algorithms that combine relevance, keywords, and signals.",
                        Map.of("title", "Search Engine Basics", "genre", "tech", "year", 2020, "rating", 8.1, "topic", "search")
                ),
                new Document(
                        "Observability uses logs, metrics, and traces to monitor system health and debug distributed applications.",
                        Map.of("title", "System Observability", "genre", "tech", "year", 2023, "rating", 8.5, "topic", "monitoring")
                ),
                new Document(
                        "REST services use stateless HTTP methods like GET and POST to build scalable and interoperable web APIs.",
                        Map.of("title", "RESTful Services", "genre", "tech", "year", 2019, "rating", 8.0, "topic", "api")
                )
        );
    }


}

