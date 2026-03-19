package com.example.project.springai.service;

import com.example.project.springai.dto.Joke;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public float[] embed(String text){
        return embeddingModel.embed(text);
    }

    public void ingestDataToVectorStore(){
        List<Document> movies = returnMoviesDocumentList();
        vectorStore.add(movies);
    }

    public  List<Document> similaritySearch(String text){
//       return vectorStore.similaritySearch(text);
        return vectorStore.similaritySearch(SearchRequest.builder()
                        .query(text)
                        .topK(3) //returns top K most similar items, or K most similar vectors
                        .similarityThreshold(0.3) // only includes result that're above this similarity score
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


}

