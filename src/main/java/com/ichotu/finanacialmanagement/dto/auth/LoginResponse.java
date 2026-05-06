package com.ichotu.finanacialmanagement.dto.auth;

import lombok.Getter;

import java.util.UUID;

@Getter
public class LoginResponse {

    private UUID id;
    private String fullName;
    private String email;
    private String token;

    public LoginResponse(UUID id, String fullName, String email, String token) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.token = token;
    }

}
