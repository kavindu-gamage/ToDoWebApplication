package com.example.todo.dto.responses;

import lombok.Data;

@Data
public class jwtResponse {
    private String token;
    private Long id;
    private String username;
    private String email;

    public jwtResponse(String token, Long id, String username, String email) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;

    }
}
