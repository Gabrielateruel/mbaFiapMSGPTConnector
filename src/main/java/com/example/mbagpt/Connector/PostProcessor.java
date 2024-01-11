package com.example.mbagpt.Connector;
import org.springframework.stereotype.Component;

@Component
public class PostProcessor {

    public String processOutput(String output) {
        String postProcessedOutput = addSuffix(output);
        return postProcessedOutput;
    }

    private String addSuffix(String input) {
        // nao fiz
        return input + " [Pós-processado]";
    }
}

