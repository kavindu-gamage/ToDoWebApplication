package com.example.todo.payloads.responses;

import lombok.Data;

@Data
public class MessageResponse {
    private String message;

    public MessageResponse(String message){
        this.message = message;

    }
}
