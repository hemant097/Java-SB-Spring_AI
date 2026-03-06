package com.example.project.springai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {
    private final ChatClient chatClient;

    public String getJokeOnTopic(String topic){
        var response =  chatClient.prompt()
                .system("you are a sarcastic joker, give response in 1 line only")
                .user("give me a joke on topic: "+topic)
                .call()
                .chatClientResponse();

        return response.chatResponse().getResult().getOutput().getText();


    }
}
