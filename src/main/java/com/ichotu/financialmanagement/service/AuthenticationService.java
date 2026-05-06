package com.ichotu.financialmanagement.service;

import com.ichotu.financialmanagement.dto.auth.LoginResponse;
import com.ichotu.financialmanagement.entity.User;
import com.ichotu.financialmanagement.repository.UserRepository;
import com.ichotu.financialmanagement.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User register(String fullName, String email, String password) {

        // Check Duplicate
        if (userRepository.existsByEmailIgnoreCase(email)){
            throw new RuntimeException("Email Already Exists");
        }

        // Create User
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setCreatedAt(OffsetDateTime.now());
        user.setUpdatedAt(OffsetDateTime.now());

        // Save User
        return userRepository.save(user);
    }

    public LoginResponse login(String email, String password){
        var user = userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(password, user.getPasswordHash())){
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new LoginResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                token
        );
    }

}
