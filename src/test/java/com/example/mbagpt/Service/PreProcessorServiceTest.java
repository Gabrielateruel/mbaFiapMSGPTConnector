package com.example.mbagpt.Service;

import com.example.mbagpt.Model.MessageDTO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PreProcessorServiceTest {

    private PreProcessorService preProcessorService;

    @Before
    public void setUp() {
        preProcessorService = new PreProcessorService();
    }

    @Test
    public void testProcessInput() {
        String input = "This is a test input!";
        String processedInput = preProcessorService.processInput(input);
        assertEquals("test input", processedInput);

        input = "@#$%^";
        processedInput = preProcessorService.processInput(input);
        assertEquals("", processedInput);
    }

    @Test
    public void testRemoveSpecialCharacters() {
        String input = "This is a test input!";
        String result = preProcessorService.removeSpecialCharacters(input);
        assertEquals("This is a test input", result);

    }

    @Test
    public void testTokenize() {
        String input = "This is a test input";
        String[] tokens = preProcessorService.tokenize(input);
        System.out.println(tokens);
        assertArrayEquals(new String[] { "This", "is", "a", "test", "input" }, tokens);

    }

    @Test
    public void testRemoveStopWords() {
        String[] tokens = { "This", "is", "a", "test", "input" };
        String result = preProcessorService.removeStopWords(tokens);
        assertEquals("test input", result);

    }

    @Test
    public void testIsStopWord() {
        assertTrue(preProcessorService.isStopWord("the"));

        assertFalse(preProcessorService.isStopWord("example"));

    }

    @Test
    public void testGenerateAssistantMessage() {
        MessageDTO messageDTO = preProcessorService.generateAssistantMessage();
        assertNotNull(messageDTO);
        assertEquals("assitant", messageDTO.getRole());
        assertTrue(messageDTO.getContent().contains("Your are a helpful assistant"));

    }
}
