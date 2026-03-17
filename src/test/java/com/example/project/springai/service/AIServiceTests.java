package com.example.project.springai.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AIServiceTests {

    @Autowired
    private AIService aiService;
    @Test
    void getJoke(){
        String joke = aiService.getJokeOnTopic("Kangaroos");
        System.out.println(joke);
    }

    @Test
    void getEmbedding(){
        float[] floats = aiService.embed("Nice apartment in Delhi");

        System.out.println(floats.length);
        for (float aFloat : floats) {
            System.out.print(aFloat+" ");
        }
    }
}
