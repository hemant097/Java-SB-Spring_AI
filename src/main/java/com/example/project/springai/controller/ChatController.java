package com.example.project.springai.controller;


import com.example.project.springai.service.AIService;
import com.example.project.springai.tool.FlightBookingTool;
import com.example.project.springai.tool.TravellingTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ai")
@RequiredArgsConstructor
public class ChatController {

    private final AIService aiService;
    private final TravellingTools travellingTools;
    private final FlightBookingTool bookingTool;
    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    @GetMapping
    public String generate(@RequestParam(defaultValue = "Tell me a joke") String topic) {

        return aiService.getJokeOnTopic(topic);
    }

    @PostMapping("/weather")
    public String chat(@RequestBody String message,
                       @RequestParam(name = "userid") String userId) {

        String systemPrompt = String.format("""
                You are a friendly flight booking assistant.
                Use the available tools to create, view, or update bookings.
                Always confirm actions with user when possible.
                
                IMPORTANT: The current user's ID is "%s".
                When calling tools, that require a userId, Always use this exact value
                """,userId);
        return chatClient.prompt()
                .system(systemPrompt)
                .user(message)
                .tools(travellingTools, bookingTool)
                .advisors(
                        MessageChatMemoryAdvisor.builder(chatMemory)
                                .conversationId(userId)
                                .build()
                )
                .call()
                .content();
    }

}