package com.example.project.springai.service;

import com.example.project.springai.dto.Joke;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {

    private final ChatClient chatClient;

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
}
