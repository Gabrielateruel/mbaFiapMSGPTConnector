package com.example.mbagpt.Service;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PostProcessorServiceTest {

    private PostProcessorService postProcessorService;

    @Before
    public void setUp() {
        postProcessorService = new PostProcessorService();
    }

    @Test
    public void testProcessOutput() {
        String output = "This is an output with special characters!!!";
        String processedOutput = postProcessorService.processOutput(output);
        assertEquals("This is an output with special characters", processedOutput);

        output = "   Output   with    extra     spaces \t and \t tabs   ";
        processedOutput = postProcessorService.processOutput(output);
        assertEquals("Output with extra spaces and tabs", processedOutput);
    }

    @Test
    public void testCleanText() {
        String text = "   Text   with    extra     spaces \t and \t tabs   ";
        String cleanedText = PostProcessorService.cleanText(text);
        assertEquals("Text with extra spaces and tabs", cleanedText);
    }

    @Test
    public void testRemoveSpecialCharacters() {
        String input = "This is a text with special characters!!!";
        String result = postProcessorService.removeSpecialCharacters(input);
        assertEquals("This is a text with special characters", result);
    }
}