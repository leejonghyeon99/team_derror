package com.example.demo.domain.openai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenAiImg {

    private Long id;
    private Long openaiId;
    private String filename;
    private String sourcename;
}
