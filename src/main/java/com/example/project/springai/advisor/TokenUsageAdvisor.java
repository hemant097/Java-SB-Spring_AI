package com.example.project.springai.advisor;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.model.ChatResponse;

@Slf4j
public class TokenUsageAdvisor implements CallAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        long startTime = System.currentTimeMillis();

        //request phase

        //passing the request down to the chain
        ChatClientResponse advisedResponse = callAdvisorChain.nextCall(chatClientRequest);

        //extract the actual LLM response
        ChatResponse chatResponse = advisedResponse.chatResponse();

        //inspecting usage metadata
        if(chatResponse != null && chatResponse.getMetadata().getUsage() != null){
            var usage = chatResponse.getMetadata().getUsage();
            long duration = System.currentTimeMillis() - startTime ;
            log.info("Token usage : Input={}, Output={}, Total={}, time in ms={}",
                    usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens(), duration);
        }
        return advisedResponse;
    }

    @NotNull
    @Override
    public String getName() {
        return "TokenUsageAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
