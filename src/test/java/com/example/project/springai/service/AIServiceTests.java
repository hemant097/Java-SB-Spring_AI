package com.example.project.springai.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.TimeZone;

@SpringBootTest
public class AIServiceTests {

    @Autowired
    private AIService aiService;

    @BeforeAll
    static void changeTZ(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    @Disabled
    void testAskAI(){
        String ans = aiService.askAI("What is Apple");
        System.out.println(ans);
    }

    @Test
    @Disabled
    void getJoke(){
        String joke = aiService.getJokeOnTopic("Kangaroos");
        System.out.println(joke);
    }

    @Test
    @Disabled
    void getEmbedding(){
        float[] floats = aiService.embed("Nice apartment in Delhi");

        System.out.println(floats.length);
        for (float aFloat : floats) {
            System.out.print(aFloat+" ");
        }
    }

    @Test
    @Disabled
    void storeData(){
        aiService.ingestDataToVectorStore();
    }

    @Test
//    @Disabled
    void testSimilaritySearch(){
        List<Document> movies = aiService.similaritySearch("magician takes admission in a school");

        for(Document movie : movies)
            System.out.println(movie);
    }
}
