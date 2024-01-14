package com.example.mbagpt.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDTO {
    private String role;
    private String content;
}
