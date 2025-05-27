package com.hive.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public class ProjectRequest {
    
    @NotBlank(message = "Project name is required")
    @Size(max = 100)
    private String name;
    
    @Size(max = 500)
    private String description;
    
    private List<String> tags;
    
    // Constructors
    public ProjectRequest() {}
    
    public ProjectRequest(String name, String description, List<String> tags) {
        this.name = name;
        this.description = description;
        this.tags = tags;
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}
