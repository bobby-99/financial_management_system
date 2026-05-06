package com.ichotu.finanacialmanagement.dto.auth;

import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getToken(){
        return token;
    }
}
