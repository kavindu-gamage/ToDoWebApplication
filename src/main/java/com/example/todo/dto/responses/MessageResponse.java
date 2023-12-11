package com.example.todo.dto.responses;

import lombok.Data;

@Data
public class MessageResponse {
    private String message;
    private int errorCode;

    public MessageResponse(int errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;

    }
}
