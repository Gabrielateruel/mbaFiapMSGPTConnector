package com.example.mbagpt.Service;

import org.springframework.stereotype.Service;

@Service
public class PostProcessorService {

    public String processOutput(String output) {
        String postProcessedOutput = cleanText(output);
        postProcessedOutput = removeSpecialCharacters(postProcessedOutput);
        return postProcessedOutput;
    }

    private static String cleanText(String text) {
        return text.trim().replaceAll("\\s+", " ").replaceAll("\\t", "   ");
    }

    private String removeSpecialCharacters(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", " ");
    }
}
