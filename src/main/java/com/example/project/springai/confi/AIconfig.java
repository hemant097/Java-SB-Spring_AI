package com.example.project.springai.confi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.groups.ConvertGroup;

@Configuration
public class AIconfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder ){
        return builder
                .build();
    }
}
