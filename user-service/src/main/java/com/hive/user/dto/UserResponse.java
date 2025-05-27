package com.hive.user.dto;

import com.hive.user.entity.User;
import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private User.Role role;
    private String organization;
    private LocalDateTime createdAt;
    
    // Constructors
    public UserResponse() {}
    
    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.organization = user.getOrganization();
        this.createdAt = user.getCreatedAt();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public User.Role getRole() { return role; }
    public void setRole(User.Role role) { this.role = role; }
    
    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
