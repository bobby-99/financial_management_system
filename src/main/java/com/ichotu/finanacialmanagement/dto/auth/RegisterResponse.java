package com.ichotu.finanacialmanagement.dto.auth;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RegisterResponse {

    private UUID id;
    private String fullName;
    private String email;

    public RegisterResponse(UUID id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

}
