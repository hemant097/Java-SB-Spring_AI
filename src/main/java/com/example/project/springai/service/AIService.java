package com.example.project.springai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {
    private final ChatClient chatClient;

    public String getJokeOnTopic(String topic){
        return chatClient.prompt()
                .user("give me a joke on topic: "+topic)
                .call()
                .content();
    }
}
