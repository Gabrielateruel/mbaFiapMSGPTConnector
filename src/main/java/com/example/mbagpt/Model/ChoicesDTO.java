package com.example.mbagpt.Model;

import org.apache.logging.log4j.message.Message;

import lombok.Data;

@Data
public class ChoicesDTO {

    private int index;
    private Message message;

}
