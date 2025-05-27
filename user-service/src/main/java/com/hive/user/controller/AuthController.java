package com.hive.user.controller;

import com.hive.user.dto.AuthResponse;
import com.hive.user.dto.UserLoginRequest;
import com.hive.user.dto.UserRegistrationRequest;
import com.hive.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRegistrationRequest request) {
        AuthResponse response = userService.register(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserLoginRequest request) {
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestHeader("Authorization") String token) {
        // Implement token refresh logic
        return ResponseEntity.ok("Token refreshed");
    }
}
