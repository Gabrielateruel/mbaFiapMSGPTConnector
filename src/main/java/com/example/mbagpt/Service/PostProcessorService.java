package com.example.mbagpt.Service;

import org.springframework.stereotype.Service;

@Service
public class PostProcessorService {

    public String processOutput(String output) {
        String postProcessedOutput = removeSpecialCharacters(output);
        postProcessedOutput = cleanText(postProcessedOutput);
        return postProcessedOutput;
    }

    public static String cleanText(String text) {
        return text.replaceAll("\\s+", " ").replaceAll("\\t", "   ").trim();
    }

    public String removeSpecialCharacters(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", " ").trim();
    }
}
