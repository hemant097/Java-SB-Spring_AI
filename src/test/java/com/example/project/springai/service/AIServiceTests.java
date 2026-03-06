package com.example.project.springai.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTests {

    @Autowired
    private AIService aiService;
    @Test
    void getJoke(){
        String joke = aiService.getJokeOnTopic("poverty");
        System.out.println(joke);
    }
}
