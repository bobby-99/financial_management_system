package com.ichotu.financialmanagement.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {

    private String fullName;
    private String email;
    private String password;

}
