package com.example.mbagpt.Controller;
import com.example.mbagpt.Connector.GptMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gpt")
public class GptController {

    private final GptMicroservice gptMicroservice;

    @Autowired
    public GptController(GptMicroservice gptMicroservice) {
        this.gptMicroservice = gptMicroservice;
    }

    @PostMapping("/generate-text")
    public String generateText(@RequestBody String input) {
        // Endpoint para gerar texto usando GPT
        return gptMicroservice.generateText(input);
    }
}
