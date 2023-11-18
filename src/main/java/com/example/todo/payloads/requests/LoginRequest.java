package com.example.todo.payloads.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
