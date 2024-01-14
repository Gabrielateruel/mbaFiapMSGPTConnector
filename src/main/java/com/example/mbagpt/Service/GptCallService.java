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
            gptResponse = callGptApi(preProcessedInput);
        } catch (Exception e) {
            // Tratar a exceção conforme necessário
            e.printStackTrace();
            throw e;
        }
        String postProcessedOutput = postProcessor.processOutput(gptResponse.getBody());
        return postProcessedOutput;
    }

    private ResponseEntity<String> callGptApi(String input) {

        List<MessageDTO> messages = buildListMessages(input);
        ChatRequestDTO request = ChatRequestDTO.builder().messages(messages).build();

        // Criando cabeçalhos para a requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        // Montando o corpo da requisição
        String requestBody = "{\"messages\": " + request.messagesToObjArray() + "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Chamada à API do GPT
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                request.getUrl(),
                HttpMethod.POST,
                requestEntity,
                String.class);

    }

    private List<MessageDTO> buildListMessages(String input) {
        MessageDTO message = MessageDTO.builder().role("User").content(input).build();
        MessageDTO assistantMessage = preProcessor.generateAssistantMessage();
        List<MessageDTO> messages = new ArrayList<MessageDTO>();
        messages.add(assistantMessage);
        messages.add(message);
        return messages;
    }
}
