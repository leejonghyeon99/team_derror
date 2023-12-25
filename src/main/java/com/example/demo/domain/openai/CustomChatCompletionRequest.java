package com.example.demo.domain.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.*;

import java.util.List;
import java.util.Map;



@EqualsAndHashCode(callSuper = true)
@Data
public class CustomChatCompletionRequest extends ChatCompletionRequest {

    @JsonProperty("response_format")
    public Map<String,String> responseFormat;





}
