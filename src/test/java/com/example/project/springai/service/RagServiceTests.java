package com.example.project.springai.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.TimeZone;

@SpringBootTest
public class RagServiceTests {

    @Autowired
    private RagService ragService;

    @BeforeAll
    static void changeTZ(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    @Disabled
    void testAskToAnswerAiAndTechQuestions(){
        String ans = ragService.askAI("What is Apple");
        System.out.println(ans);
    }

    @Test
    @Disabled
    void ingestPdfToVectorDB(){
        ragService.ingestPdfToVectorStore();
    }

    @Test
    @Disabled
    void testAskToAnswerFromFaqPdf(){
        String ans = ragService.askAI("cant view recordings player not working");
        System.out.println(ans);
    }

    @Test
    void testAskAiWithAdvisor(){
       String res =  ragService.askAiWithAdvisors("How is the weather there now, I am asking","paul123");
        System.out.println(res);
    }

}
