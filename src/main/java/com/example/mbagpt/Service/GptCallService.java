package com.example.mbagpt.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.example.mbagpt.Model.ChatRequestDTO;
import com.example.mbagpt.Model.MessageDTO;

@Service
public class GptCallService {

    @Autowired
    private PreProcessorService preProcessor;

    @Autowired
    private PostProcessorService postProcessor;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    public String generateText(String input) {
        String preProcessedInput = preProcessor.processInput(input);
        ResponseEntity<String> gptResponse;
        try {
            gptResponse = callGptApi(preProcessedInput, new RestTemplate());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        String postProcessedOutput = postProcessor.processOutput(gptResponse.getBody());
        return postProcessedOutput;
    }

    public ResponseEntity<String> callGptApi(String input, RestTemplate restTemplate) {
        List<MessageDTO> messages = buildListMessages(input);
        ChatRequestDTO request = ChatRequestDTO.builder().messages(messages).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        String requestBody = "{\"messages\": " + request.messagesToObjArray() + "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(
                request.getUrl(),
                HttpMethod.POST,
                requestEntity,
                String.class);
    }

    public List<MessageDTO> buildListMessages(String input) {
        MessageDTO message = MessageDTO.builder().role("User").content(input).build();
        MessageDTO assistantMessage = preProcessor.generateAssistantMessage();
        List<MessageDTO> messages = new ArrayList<MessageDTO>();
        messages.add(assistantMessage);
        messages.add(message);
        return messages;
    }
}
