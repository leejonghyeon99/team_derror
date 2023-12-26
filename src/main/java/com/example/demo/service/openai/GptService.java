package com.example.demo.service.openai;

import com.example.demo.domain.openai.Recommendations;
import com.example.demo.domain.openai.Sight;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;

import java.util.List;

public interface GptService {

    List<Recommendations> settingSight(Long id);

    List<ChatCompletionChoice> chat(String prompt);

    Sight getSightJson(String prompt);

    void saveImagesFromUrls(List<String> imageUrls);
}
