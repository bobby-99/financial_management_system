package com.ichotu.finanacialmanagement.controller;

import com.ichotu.finanacialmanagement.dto.auth.LoginRequest;
import com.ichotu.finanacialmanagement.dto.auth.LoginResponse;
import com.ichotu.finanacialmanagement.dto.auth.RegisterRequest;
import com.ichotu.finanacialmanagement.dto.auth.RegisterResponse;
import com.ichotu.finanacialmanagement.entity.User;
import com.ichotu.finanacialmanagement.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {

        var user = authenticationService.register(
                request.getFullName(),
                request.getEmail(),
                request.getPassword()
        );

        return new RegisterResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail()
        );

    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return authenticationService.login(
                request.getEmail(),
                request.getPassword()
        );
    }

}
