package com.example.demo.repository.openai;

import com.example.demo.domain.openai.OpenAiImg;
import com.example.demo.domain.openai.Recommendations;
import com.example.demo.domain.openai.Sight;

import java.util.List;

public interface OpenaiRepository {

    List<Recommendations> findById(Long id);

    int save(Recommendations recommendations);

    int saveImg(OpenAiImg img);
}
