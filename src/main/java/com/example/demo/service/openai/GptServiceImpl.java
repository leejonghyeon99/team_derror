package com.example.demo.service.openai;

import com.example.demo.domain.board.U;
import com.example.demo.domain.openai.CustomChatCompletionRequest;
import com.example.demo.domain.openai.OpenAiImg;
import com.example.demo.domain.openai.Recommendations;
import com.example.demo.domain.openai.Sight;
import com.example.demo.domain.user.Member;
import com.example.demo.repository.openai.OpenaiRepository;
import com.example.demo.repository.user.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.messages.Message;
import com.theokanning.openai.messages.MessageRequest;
import com.theokanning.openai.runs.Run;
import com.theokanning.openai.runs.RunCreateRequest;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.threads.Thread;
import com.theokanning.openai.threads.ThreadRequest;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Autor 이종현
 *
 */
@Service
public class GptServiceImpl implements GptService{

    @Value("${app.upload.path}")
    private String uploadDir;

    @Value(value = "${openai.key}")
    private String token;

    private OpenaiRepository openaiRepository;

    private UserRepository userRepository;

    @Autowired
    public GptServiceImpl(SqlSession session) {
        this.openaiRepository = session.getMapper(OpenaiRepository.class);
        this.userRepository = session.getMapper(UserRepository.class);
    }

    @Override
    public List<Recommendations> settingSight(Long id) {
        return openaiRepository.findById(id);
    }

    /**
     * @Author 이종현
     * @param prompt
     * @return
     */
    public List<ChatCompletionChoice> chat(String prompt){
        OpenAiService service = new OpenAiService(token);

        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), prompt);
        messages.add(systemMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-4")
                .messages(messages)
                .n(1)
                .maxTokens(255)
                .logitBias(new HashMap<>())
                .build();
        return service.createChatCompletion(chatCompletionRequest).getChoices();

    }



    String json = """
            {
              "recommendation": {
                "national":"관광지 국가 이름",
                "countryCode":"국가 코드",
                "city":"관광지 도시 이름",
                "place":"관광지 지역이름",
                "address":"관광지 주소",
                "longitude":"경도",
                "latitude":"위도",
                "detail":"관광지 설명",
                "language":"해당 국가언어"   
              }
            }
            """;

    public Sight getSightJson(String prompt) {
        OpenAiService service = new OpenAiService(token,Duration.ofSeconds(25));

        CustomChatCompletionRequest completionRequest = getCustomChatCompletionRequest(prompt);

        System.out.println("Openai Request Start");
        long startTime = System.currentTimeMillis();

        ChatCompletionChoice choice = service.createChatCompletion(completionRequest).getChoices().get(0);

        long endTime = System.currentTimeMillis();
        long elapsedTimeInSeconds = (endTime - startTime) / 1000;

        System.out.println("Openai Request End: " + elapsedTimeInSeconds + "초");

        ObjectMapper mapper = new ObjectMapper();
        Sight sight = null;
        Member member = userRepository.findId(U.getLoggedUser().getId());
        try {
            sight = mapper.readValue(choice.getMessage().getContent(),Sight.class);
            sight.getRecommendation().setQuestion(prompt);
            System.out.println(sight.toString());
            sight.getRecommendation().setMemberId(member.getId());
            openaiRepository.save(sight.getRecommendation());


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return sight;

    }

    @NotNull
    private CustomChatCompletionRequest getCustomChatCompletionRequest(String prompt) {
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "관광지를 추천해주고 한글로 출력하되 다음 형식으로 출력"+json);
        final ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);
        messages.add(systemMessage);
        messages.add(userMessage);


        Map<String, String> map = new HashMap<>();
        map.put("type","json_object");

        CustomChatCompletionRequest completionRequest = new CustomChatCompletionRequest();
        completionRequest.setModel("gpt-4-1106-preview");
        completionRequest.setResponseFormat(map);
        completionRequest.setMessages(messages);
        completionRequest.setN(1);
        completionRequest.setMaxTokens(4000);
        completionRequest.setLogitBias(new HashMap<>());
        return completionRequest;
    }



    public void saveImagesFromUrls(List<String> imageUrls) {
        List<String> newList = imageUrls.stream().skip(1).collect(Collectors.toList());
        Long id = Long.parseLong(imageUrls.get(0));
        System.out.println(newList);
        for (String imageUrl : newList) {
            saveImageFromUrl(imageUrl, id);
        }
    }

    private void saveImageFromUrl(String imageUrl, Long id) {
        try {


            // 이미지 파일 이름은 고유하게 생성 또는 추출
            String fileName = generateUniqueFileName();

            // 파일 저장 경로
            Path destinationPath = Path.of(uploadDir+"/openai", fileName);

            OpenAiImg openAiImg = OpenAiImg.builder()
                    .openaiId(id)
                    .filename(fileName)
                    .build();
            // 이미지 다운로드 및 저장

            try (InputStream in = new URL(imageUrl).openStream()) {
                Files.copy(in, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                openaiRepository.saveImg(openAiImg);
            }
        } catch (IOException e) {
            e.printStackTrace(); // 오류 처리를 적절히 수행하세요.
        }
    }

    private String generateUniqueFileName() {
        // 실제로는 고유한 파일 이름을 생성하거나 추출하는 로직을 구현해야 합니다.
        // 여기서는 타임스탬프를 파일 이름에 추가하는 예시를 보여줍니다.
        return "image_" + System.currentTimeMillis() + ".jpg";
    }


}
