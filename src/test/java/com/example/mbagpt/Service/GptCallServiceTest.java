package com.example.mbagpt.Service;

import com.example.mbagpt.Model.MessageDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GptCallServiceTest {

    @InjectMocks
    private GptCallService gptCallService;

    @Spy
    private PreProcessorService preProcessor;

    @Spy
    private PostProcessorService postProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCallGptApi() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class)))
                .thenReturn(new ResponseEntity<>("GPT API response", HttpStatus.OK));

        ResponseEntity<String> result = gptCallService.callGptApi("input", restTemplate);

        verify(restTemplate).exchange(anyString(), eq(HttpMethod.POST), any(), eq(String.class));

        assertEquals("GPT API response", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testBuildListMessages() {
        List<MessageDTO> result = gptCallService.buildListMessages("input");

        assertEquals(2, result.size());
        assertEquals("User", result.get(1).getRole());
        assertEquals("input", result.get(1).getContent());
        assertEquals("assitant", result.get(0).getRole());
    }
}
