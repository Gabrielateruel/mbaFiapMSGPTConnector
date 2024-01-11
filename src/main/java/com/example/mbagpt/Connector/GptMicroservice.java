package com.example.mbagpt.Connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpHeaders; 
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Service
public class GptMicroservice {

    private final PreProcessor preProcessor;
    private final PostProcessor postProcessor;

    @Autowired
    public GptMicroservice(PreProcessor preProcessor, PostProcessor postProcessor) {
        this.preProcessor = preProcessor;
        this.postProcessor = postProcessor;
    }

    public String generateText(String input) {
        // Realizando pré-processamento
        String preProcessedInput = preProcessor.processInput(input);

        // Chamada à API do GPT com base no input pré-processado
        try {
            String gptResponse = callGptApi(preProcessedInput);

            // Realizando pós-processamento
            String postProcessedOutput = postProcessor.processOutput(gptResponse);

            return postProcessedOutput;
        } catch (Exception e) {
            // Tratar a exceção conforme necessário
            e.printStackTrace();
            return "Erro ao chamar a API do GPT.";
        }
    }

    private String callGptApi(String input) {
        // Substitua a URL pela do seu endpoint GPT
        String gptApiUrl = "URL_DO_SEU_ENDPOINT_DO_GPT";
    
        // Criando cabeçalhos para a requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    
        // Montando o corpo da requisição
        String requestBody = "{\"input\": \"" + input + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Chamada à API do GPT
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(gptApiUrl, HttpMethod.POST, requestEntity, String.class).getBody();
    }
}

