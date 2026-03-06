package com.example.project.springai.controller;


import com.example.project.springai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ai")
@RequiredArgsConstructor
public class ChatController {

        private final AIService aiService;

        @GetMapping
        public String generate(@RequestParam(defaultValue = "Tell me a joke") String topic) {

            return aiService.getJokeOnTopic(topic);
        }
    }
