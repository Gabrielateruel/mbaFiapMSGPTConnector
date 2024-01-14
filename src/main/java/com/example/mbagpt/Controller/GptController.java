package com.example.mbagpt.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mbagpt.Service.GptCallService;

@RestController
@RequestMapping("/api/gpt")
public class GptController {

    @Autowired
    private GptCallService gptMicroservice;

    @PostMapping
    public String generateText(@RequestBody String input, @RequestBody Double temperature) {
        return gptMicroservice.generateText(input);
    }
}
