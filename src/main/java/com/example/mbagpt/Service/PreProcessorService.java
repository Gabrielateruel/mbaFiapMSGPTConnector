package com.example.mbagpt.Service;
import org.springframework.stereotype.Service;

import com.example.mbagpt.Model.MessageDTO;

@Service
public class PreProcessorService {

    public String processInput(String input) {
        String cleanedInput = removeSpecialCharacters(input);
        String lowercaseInput = cleanedInput.toLowerCase();
        String[] tokens = tokenize(lowercaseInput);
        String processedInput = removeStopWords(tokens);
        return processedInput;
    }

    private String removeSpecialCharacters(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", " ");
    }

    private String[] tokenize(String input) {
        return input.split("\\s+");
    }

    private String removeStopWords(String[] tokens) {
        StringBuilder result = new StringBuilder();
        for (String token : tokens) {
            if (!isStopWord(token)) {
                result.append(token).append(" ");
            }
        }
        return result.toString().trim();
    }

    private boolean isStopWord(String word) {
        String[] stopWords = {"the", "and", "is"};
        for (String stopWord : stopWords) {
            if (stopWord.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    public MessageDTO generateAssistantMessage() {
        String content = "Your are a helpful assistant that responds to any context in detail, giving complete answers and deep explanations around the topic being questioned.";
        MessageDTO messageDTO = MessageDTO.builder().role("assitant").content(content).build();
        return messageDTO;
    }
}

