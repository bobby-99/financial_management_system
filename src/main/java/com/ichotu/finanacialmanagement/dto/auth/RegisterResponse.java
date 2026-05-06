package com.ichotu.finanacialmanagement.dto.auth;

import java.util.UUID;

public class RegisterResponse {

    private UUID id;
    private String fullName;
    private String email;

    public RegisterResponse(UUID id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
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
}
