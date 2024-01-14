package com.example.mbagpt.Model;

import java.util.List;

import lombok.Data;

@Data
public class ChatResponseDTO {

    private List<ChoicesDTO> choices;

}
