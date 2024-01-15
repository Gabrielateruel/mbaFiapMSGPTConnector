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

    public String removeSpecialCharacters(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", " ").trim();
    }

    public String[] tokenize(String input) {
        return input.split("\\s+");
    }

    public String removeStopWords(String[] tokens) {
        StringBuilder result = new StringBuilder();
        for (String token : tokens) {
            if (!isStopWord(token)) {
                result.append(token).append(" ");
            }
        }
        return result.toString().trim();
    }

    public boolean isStopWord(String word) {
        String[] stopWords = {
            "i", "me", "my", "myself", "we", "our", "ours", "ourselves",
            "you", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself",
            "she", "her", "hers", "herself", "it", "its", "itself", "they", "them", "their",
            "theirs", "themselves", "what", "which", "who", "whom", "this", "that", "these",
            "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has",
            "had", "having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if",
            "or", "because", "as", "until", "while", "of", "at", "by", "for", "with", "about",
            "against", "between", "into", "through", "during", "before", "after", "above",
            "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under",
            "again", "further", "then", "once", "here", "there", "when", "where", "why", "how",
            "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no",
            "nor", "not", "only", "own", "same", "so", "than", "too", "very", "s", "t", "can",
            "will", "just", "don", "should", "now"
        };
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

