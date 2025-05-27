package com.hive.user.service;

import com.hive.user.dto.*;
import com.hive.user.entity.User;
import com.hive.user.repository.UserRepository;
import com.hive.user.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    public AuthResponse register(UserRegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User(
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            passwordEncoder.encode(request.getPassword()),
            request.getOrganization()
        );
        
        user = userRepository.save(user);
        
        String token = jwtTokenProvider.generateToken(user.getEmail());
        
        return new AuthResponse(token, new UserResponse(user));
    }
    
    public AuthResponse login(UserLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        String token = jwtTokenProvider.generateToken(user.getEmail());
        
        return new AuthResponse(token, new UserResponse(user));
    }
    
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
            .map(UserResponse::new)
            .collect(Collectors.toList());
    }
    
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user);
    }
    
    public UserResponse updateUserRole(Long id, User.Role role) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setRole(role);
        user = userRepository.save(user);
        
        return new UserResponse(user);
    }
    
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user);
    }
}
