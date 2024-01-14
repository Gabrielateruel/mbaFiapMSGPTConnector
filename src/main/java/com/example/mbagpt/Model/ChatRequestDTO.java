package com.example.mbagpt.Model;

import java.util.Arrays;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRequestDTO {

    @Builder.Default
    private String model = "gpt-3.5-turbo";
    @Builder.Default
    private Double temperature = 0.1;
    @Builder.Default
    private String url = "https://api.openai.com/v1/chat/completions";
    private List<MessageDTO> messages;

    public Object[] messagesToObjArray(){
        return messages.toArray();
    }

}
