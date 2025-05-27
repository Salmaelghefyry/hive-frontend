package com.hive.user.controller;

import com.hive.user.dto.UserResponse;
import com.hive.user.entity.User;
import com.hive.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    @PreAuthorize("hasRole('PROJECT_ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('PROJECT_ADMIN')")
    public ResponseEntity<UserResponse> updateUserRole(
            @PathVariable Long id, 
            @RequestBody Map<String, String> request) {
        User.Role role = User.Role.valueOf(request.get("role"));
        UserResponse user = userService.updateUserRole(id, role);
        return ResponseEntity.ok(user);
    }
}
