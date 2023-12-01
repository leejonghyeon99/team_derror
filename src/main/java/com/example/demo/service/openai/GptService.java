package com.example.demo.service.openai;

import com.theokanning.openai.assistants.AssistantRequest;
import com.theokanning.openai.assistants.AssistantToolsEnum;
import com.theokanning.openai.assistants.Tool;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.FunctionExecutor;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Autor 이종현
 *
 */
@Service
public class GptService {

    @Value(value = "${openai.key}")
    private String token;

   public void test(){

       OpenAiService service = new OpenAiService(token, Duration.ofSeconds(30));

       System.out.println("\nCreating Image...");
       CreateImageRequest request = CreateImageRequest.builder()
               .model("dall-e-3")
               .quality("hd")
               .prompt("오락실에서 게임하는 거북이")
               .build();

       System.out.println("\nImage is located at:");
       System.out.println(service.createImage(request).getData().get(0).getUrl());
   }

   public void test2(){
       OpenAiService service = new OpenAiService(token, Duration.ofSeconds(30));
       System.out.println("GPT 연결");
       final List<ChatMessage> messages = new ArrayList<>();
       final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "오늘의 날씨 정보 알려줘");
       messages.add(systemMessage);
       ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
               .builder()
               .model("gpt-3.5-turbo")
               .messages(messages)
               .n(1)
               .maxTokens(50)
               .logitBias(new HashMap<>())
               .build();
       service.createChatCompletion(chatCompletionRequest)
               .getChoices().forEach(System.out::println);

       //OpenAi 연결 종료
       service.shutdownExecutor();
       System.out.println("GPT 종료");
   }

   public void test3(){
       OpenAiService service = new OpenAiService(token);
       String instructions = "입력받은 키워드로 명소 추천" +
               "최대 5개 명사만 출력" +
               "명사로만 출력하고 경도, 위도 같이 출력" +
               "출력형식" +
               "{" +
               "  \"recommendations\": [" +
               "    {" +
               "      \"place\": String," +
               "      \"longitude\": String," +
               "      \"latitude\": String" +
               "    }," +
               "  ]" +
               "}";
       List<Tool> tools = new ArrayList<>();
       Tool tool = new Tool();
       tool.setType(AssistantToolsEnum.CODE_INTERPRETER);
       tools.add(tool);
       AssistantRequest assistantRequest = AssistantRequest
               .builder()
               .name("recommendation")
               .model("gpt-3.5-turbo-1106")
               .instructions("입력받은 문자열을 연상되는 단어만 답해줘")
               .tools(tools)
               .build();

   }
}
